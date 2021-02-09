package com.example.ISA2020.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.repository.DermWorkHoursRepository;
import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.dto.DermatologistDTO;
import com.example.ISA2020.entity.DermWorkHours;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.EntityStatus;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.service.DermatologistService;
import com.example.ISA2020.service.ExaminationService;


@Service
public class DermatologistServiceImpl implements DermatologistService{
	
	@Autowired
	private DermatologistRepository dermatologistRepository;
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	private DermWorkHoursRepository dermWorkHourRepository;
	
	@Autowired
	private ExaminationService examinationService;
	
	
	
	
	@Override
	public Dermatologist findById(Long id) {
		return dermatologistRepository.findOneById(id);
	}

	@Override
	public Dermatologist findByUsername(String username) {
		return dermatologistRepository.findOneByUsername(username);
	}

	@Override
	public List<Dermatologist> getAllDermatologists() {
		return dermatologistRepository.findAll();
	}
	
	@Override
	public Dermatologist getDermatologist(Long id) {
		
		return dermatologistRepository.findOneById(id);
	}
	
	@Override
	public List<DermatologistDTO> getAllActiveDermatologists() {
		return convertToDTO(dermatologistRepository.findByStatusNot(UserStatus.DELETED));
	}

	
	@Override
	public List<DermatologistDTO> findAllDermatologistsInPharmacy(Pharmacy pharmacy) {
		
		List<DermatologistDTO> dermsDTO = new ArrayList<DermatologistDTO>();
		
		List<DermWorkHours> dermWorkHours = dermWorkHourRepository.findByPharmacyIdAndStatusNot(pharmacy.getId(), EntityStatus.DELETED);
		for(DermWorkHours temp : dermWorkHours) {
			dermsDTO.add(new DermatologistDTO(temp.getDermatologist().getId(),
											  temp.getDermatologist().getUsername(),
											  temp.getDermatologist().getFirstName(),
											  temp.getDermatologist().getLastName(),
											  temp.getDermatologist().getPhoneNumber(),
											  temp.getTimeFrom().toString(),
											  temp.getTimeTo().toString()
											  ));
		}
		return dermsDTO;
	}
	
	@Override
	public List<DermatologistDTO> getAllAvailableDermatologists(Pharmacy pharmacy, String startDateTime, String endDateTime) {
		
		List<DermatologistDTO> dermsDTO = new ArrayList<>();
		
		List<DermWorkHours> dermWorkHours = dermWorkHourRepository.findByPharmacyIdAndStatusNot(pharmacy.getId(), EntityStatus.DELETED);
		
		System.out.println("Slobodni dermatolozi: ");
		for(DermWorkHours temp : dermWorkHours) {
			Dermatologist tempDerm = temp.getDermatologist();
			
			if(isAvailable(tempDerm, getLocalDateTime(startDateTime), getLocalDateTime(endDateTime) )) {
				dermsDTO.add(new DermatologistDTO(tempDerm));
				System.out.println(tempDerm.getFirstName() + " " + tempDerm.getLastName() );
			}
		}
		
		return dermsDTO;
	}
	
	  private boolean isAvailable(Dermatologist derm, LocalDateTime localDateTime, LocalDateTime localDateTime2) {
		//zasad zakucaj JBG !!!
		return true;
	}

	
	
	@Override
	public List<DermatologistDTO> searchDermatologistsInPharmacy(Long id, String firstName, String lastName) {
		
		List<DermatologistDTO> dermsDTO = new ArrayList<DermatologistDTO>();
		
		List<Dermatologist> derms = dermatologistRepository.findByStatusNotAndFirstNameContainsIgnoringCaseAndLastNameContainsIgnoringCase(UserStatus.DELETED, firstName, lastName);
		
		List<DermWorkHours> dermWorkHours = dermWorkHourRepository.findByPharmacyIdAndStatusNot(id, EntityStatus.DELETED);
		for(DermWorkHours temp : dermWorkHours) {
			if(derms.contains(temp.getDermatologist())) {
				dermsDTO.add(new DermatologistDTO(temp.getDermatologist().getId(),
												  temp.getDermatologist().getUsername(),
												  temp.getDermatologist().getFirstName(),
												  temp.getDermatologist().getLastName(),
												  temp.getDermatologist().getPhoneNumber(),
												  temp.getTimeFrom().toString(),
												  temp.getTimeTo().toString()
												  ));
			}
		}
		return dermsDTO;
	}
	
	
	
	
	
	
  
	private LocalDateTime getLocalDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

	@Override
	public DermatologistDTO deleteDermatologistFromPharmacy(Long pharmacyId, Long id) {
		
		DermWorkHours derm = dermWorkHourRepository.findByPharmacyIdAndDermatologistId(pharmacyId,id);
		if(derm == null) {
			return null;
		}
		if(derm.getStatus() == EntityStatus.DELETED) {
			return null;
		}
		
		Pharmacy pharmacy = derm.getPharmacy();
		Dermatologist dermatologist = derm.getDermatologist();
		
		//Provera da li dermatolog ima zakazane preglede
		Set<Examination> exams = dermatologist.getExaminations();
		for(Examination exam : exams) {
			if(exam.getStatus() == ExaminationStatus.BOOKED) {
				return null;
			}
		}
		
		derm.setStatus(EntityStatus.DELETED);
		
		pharmacy.getDermsWithWorkHours().remove(derm);
		dermatologist.getWorkHours().remove(derm);
		
		dermWorkHourRepository.save(derm);
		return new DermatologistDTO(dermatologistRepository.save(dermatologist));

	}

	
	
	
	
	
	
	//pomocne metode
	private boolean HasExaminationsToDo(Long dermId) {
		
		List<Examination> examinations = examinationService.getDermatologistUpcomingExaminations(dermId);
		
		if(examinations != null && !examinations.isEmpty()) {
			return true;
		}
		
		return false;
	}

	private List<DermatologistDTO> convertToDTO(List<Dermatologist> dermatologists){
		
		 List<DermatologistDTO> dermatologistDTOs = new ArrayList<>();
	        for (Dermatologist dermatologist : dermatologists) {
	        	dermatologistDTOs.add(new DermatologistDTO(dermatologist));
	        }
	        return dermatologistDTOs;
		
	}



}
