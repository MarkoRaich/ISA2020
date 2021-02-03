package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.dto.PatientWithIdDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.repository.AuthRepository;
import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.DrugRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.repository.PharmacyAdminRepository;
import com.example.ISA2020.repository.SupplierRepository;
import com.example.ISA2020.repository.SystemAdminRepository;
import com.example.ISA2020.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired 
	private DrugRepository drugRepo;
	
	

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
	
	
	
}
