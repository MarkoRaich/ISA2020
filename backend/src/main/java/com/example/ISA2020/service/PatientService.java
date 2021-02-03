package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.entity.users.Patient;

public interface PatientService {
	
	Patient findById(Long id);
	
	Patient findByUsername(String username);
	
	List<Patient> getAllPatients();
	
	Patient getLoginPatient();
	
	Patient changePassword(String newPassword, Patient user);
	
	PatientDTO editPersonalInformation(EditPatientDTO editPatientDTO);
	
	Patient addAlergie(String drugName);
}
