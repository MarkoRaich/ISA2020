package com.example.ISA2020.service.Impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.PharmacistDTO;
import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.repository.ConsultationPriceRepository;
import com.example.ISA2020.repository.ExaminationPriceRepository;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.service.AuthService;
import com.example.ISA2020.service.ConsultationPriceService;
import com.example.ISA2020.service.ConsultationService;
import com.example.ISA2020.service.PharmacistService;
import com.example.ISA2020.service.UserService;

@Service
public class PharmacistServiceImpl implements PharmacistService{
	
	@Autowired
	private PharmacistRepository pharmacistRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService; 
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private ExaminationPriceRepository examinationPriceRepo;
	
	@Autowired 
	private ConsultationPriceService consultationPriceService;
	
	
	
	
	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepo.findOneById(id);
	}

	@Override
	public Pharmacist findByUsername(String username) {
		return pharmacistRepo.findOneByUsername(username);
	}

	@Override
	public List<Pharmacist> getAllPharmacists() {
		return pharmacistRepo.findAll();
	}

	@Override
	public List<PharmacistDTO> findAllPharmacistsInPharmacy(Long id) {
		
		return convertToDTO(pharmacistRepo.findByPharmacyIdAndStatusNot(id, UserStatus.DELETED));
	}

	@Override
	public List<PharmacistDTO> searchPharmacistsInPharmacy(Long id, String firstName, String lastName) {
		return convertToDTO(pharmacistRepo.findByPharmacyIdAndStatusNotAndFirstNameContainsIgnoringCaseAndLastNameContainsIgnoringCase
										   (id, UserStatus.DELETED, firstName, lastName));
	}
	
	@Override
	public Pharmacist getPharmacist(Long id) {
		return pharmacistRepo.findByIdAndStatusNot(id, UserStatus.DELETED);
	}
	
	@Override
	public PharmacistDTO deletePharmacist(Long pharmacyId, Long id) {
		
		Pharmacist pharmacist = getPharmacist(id);
		
		if(pharmacist == null) {
			return null;
		}
		
		if(pharmacist.getPharmacy().getId() != pharmacyId) {
			return null;
		}
		
		//provera da li farmaceut ima zakazanih termina
		Set<Consultation> consultations = pharmacist.getConsultations();
		for(Consultation cons : consultations) {
			
			if(cons.getStatus() == ConsultationStatus.BOOKED) {
				return null;
			}
		}
		
		pharmacist.setStatus(UserStatus.DELETED);
		return new PharmacistDTO(pharmacistRepo.save(pharmacist));
	}
		
		
	@Override
	public PharmacistDTO create(@Valid PharmacistDTO pharmacistDTO, Pharmacy pharmacy) {
		
		 UserDetails userDetails = userService.searchUserInAllRepositories(pharmacistDTO.getEmail());
	        if (userDetails != null) {
	            return null;
	        }
	     
        LocalTime workHoursFrom = LocalTime.parse(pharmacistDTO.getWorkHoursFrom(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime workHoursTo = LocalTime.parse(pharmacistDTO.getWorkHoursTo(), DateTimeFormatter.ofPattern("HH:mm"));
        if (workHoursFrom.isAfter(workHoursTo)) {
            return null;
        }
		
        String generatedPassword = "lozinka";
        String hashedPassword = passwordEncoder.encode(generatedPassword);
        
        Set<Authority> authorities = authService.findByName("ROLE_PHARMACIST");
        
	    Pharmacist newPharmacist = new Pharmacist(pharmacistDTO.getEmail(), 
									        	  hashedPassword,
									        	  pharmacistDTO.getFirstName(),
									        	  pharmacistDTO.getLastName(), 
									        	  workHoursFrom, 
									        	  workHoursTo,
									        	  pharmacistDTO.getPhoneNumber(),
									        	  pharmacy,
									        	  authorities); 	
        										 
        Pharmacist pharmacist = pharmacistRepo.save(newPharmacist);
        
        return new PharmacistDTO(pharmacistRepo.save(pharmacist));
        
        
        
	}
		
		
	private List<PharmacistDTO> convertToDTO(List<Pharmacist> doctors) {
        List<PharmacistDTO> PharmacistDTOs = new ArrayList<>();
        for (Pharmacist pharmacist : doctors) {
        	PharmacistDTOs.add(new PharmacistDTO(pharmacist));
        }
        return PharmacistDTOs;
    }
	
	


	
	
	

	



	
}
