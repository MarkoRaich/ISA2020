package com.example.ISA2020.service.Impl;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.dto.PatientWithIdDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.repository.DrugPriceRepository;
import com.example.ISA2020.repository.DrugRepository;
import com.example.ISA2020.repository.ExaminationPriceRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.ExaminationPriceService;
import com.example.ISA2020.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired 
	private DrugRepository drugRepo;
	
	@Autowired 
	private PharmacyRepository pharmacyRepo;
	
	@Autowired 
	private DrugPriceRepository drugPriceRepo;
	
	@Autowired
	private ExaminationPriceRepository examinationPriceRepo;
	
	
	

	@Override
	public Patient findById(Long id) {
		return patientRepo.findOneById(id);
	}

	@Override
	public Patient findByUsername(String username) {
		return patientRepo.findOneByUsername(username);
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepo.findAll();
	}
	
	
	@Override
    public Patient getLoginPatient() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            Patient patient = patientRepo.findOneByUsername(currentUser.getName());
            if (patient != null) {
                return patient;
            }
        } catch (UsernameNotFoundException ex) {

        }
        return null;
    }

	private List<PatientWithIdDTO> convertToDTO(List<Patient> patients) {
        if (patients == null || patients.isEmpty()) {
            return new ArrayList<>();
        }
        List<PatientWithIdDTO> patientWithIdDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            patientWithIdDTOS.add(new PatientWithIdDTO(patient));
        }
        return patientWithIdDTOS;
    }
	
	@Override
    public Patient changePassword(String newPassword, Patient user) {
		//da li treba neka provera ili odobrenje da mu se zada kod menjanja lozinke??
        user.setPassword(newPassword);
        return patientRepo.save(user);
    }
	
	@Override
	public PatientDTO editPersonalInformation(EditPatientDTO editPatientDTO) {
		Patient patient = getLoginPatient();
		
		if(patient.getId() != editPatientDTO.getId()) {
			return null;
		}
		
		patient.setFirstName(editPatientDTO.getFirstName());
		patient.setLastName(editPatientDTO.getLastName());
		patient.setAddress(editPatientDTO.getAddress());
		patient.setCity(editPatientDTO.getCity());
		patient.setPhoneNumber(editPatientDTO.getPhoneNumber());
		
		return new PatientDTO(patientRepo.save(patient));
		
	}
	
	@Override
	public Patient addAlergie(String drugName) {
		Patient patient = getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		List<Drug> drugs = drugRepo.findAll();
		
		for(Drug d : drugs) {
			if(d.getName().toLowerCase().contains(drugName.toLowerCase())) {
				patient.getAlergies().add(d);			
			}
		}
		
		patientRepo.save(patient);
		
		return patient;
		
	}
	
	/*
	 * @Override public List<DrugPricePharmacyNameAddressRatingDTO> getAll
	 */
	
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice() {
		Patient patient = getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByPrice();
		List<ExaminationPrice> patientExaminations = new ArrayList<>();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ExaminationPrice p : prices) {
			if(p.getExamination().getPatient().getId() == patient.getId()) {
				patientExaminations.add(p);
			}
		}
		
			//pretvaranje rezultata u dto modele
		for(ExaminationPrice e : patientExaminations) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getInterval().getStartDateTime());
			dto.setEndDateTime(e.getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByDate() {
		Patient patient = getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByPrice();
		List<ExaminationPrice> patientExaminations = new ArrayList<>();
		List<ExaminationPrice> sortiranoPoDatumu = new ArrayList<>();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ExaminationPrice p : prices) {
			if(p.getExamination().getPatient().getId() == patient.getId()) {
				patientExaminations.add(p);
			}
		}
		
		/*
		for(ExaminationPrice p1 : patientExaminations) {
			for(ExaminationPrice p2 : patientExaminations) {
				if(p1.getId() != p2.getId()) {
					if(assertThat(p1.getInterval().getStartDateTime().isBefore(p2.getInterval().getStartDateTime()), is(true))){
						sortiranoPoDatumu.add(p1);
					}else {
						sortiranoPoDatumu.add(p2);
					}
				}
			}
		} */
		
			//pretvaranje rezultata u dto modele
		for(ExaminationPrice e : patientExaminations) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getInterval().getStartDateTime());
			dto.setEndDateTime(e.getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	
	
	
}
