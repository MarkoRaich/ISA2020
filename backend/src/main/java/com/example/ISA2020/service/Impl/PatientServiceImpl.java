package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepo;

	@Override
	public Patient findById(Long id) {
		// TODO Auto-generated method stub
		return patientRepo.findOneById(id);
	}

	@Override
	public Patient findByUsername(String username) {
		// TODO Auto-generated method stub
		return patientRepo.findOneByUsername(username);
	}

	@Override
	public List<Patient> getAllPatients() {
		// TODO Auto-generated method stub
		return patientRepo.findAll();
	}
	
}
