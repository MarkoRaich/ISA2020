package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.users.Patient;

public interface PatientService {
	
	Patient findById(Long id);
	
	Patient findByUsername(String username);
	
	List<Patient> getAllPatients();
	
	Patient create(Patient patient);
}
