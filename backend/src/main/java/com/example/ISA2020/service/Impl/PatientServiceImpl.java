package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.controller.DermatologistRepository;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.repository.AuthRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.repository.PharmacyAdminRepository;
import com.example.ISA2020.repository.SupplierRepository;
import com.example.ISA2020.repository.SystemAdminRepository;
import com.example.ISA2020.security.auth.JwtAuthenticationRequest;
import com.example.ISA2020.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired 
	private SystemAdminRepository systemAdminRepo;
	
	@Autowired
	private PharmacyAdminRepository pharmacyAdminRepo;
	
	@Autowired
	private DermatologistRepository dermatologistRepo;
	
	@Autowired
	private PharmacistRepository pharmacistRepo;
	
	@Autowired
	private SupplierRepository supplierRepo;
	
	@Autowired 
	private AuthRepository authRepo;
	
	

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
	
	public Patient create(Patient patient) {
		if(patientRepo.findOneById(patient.getId()) != null) {
			System.out.println("Pacijent sa datim Id-om vec postoji.");
			return null; //!= ili == ???
		}
		
		if(patientRepo.findOneByUsername(patient.getUsername()) != null) {
			System.out.println("Pacijent sa datim Username-om vec postoji.");
			return null;
		}
		
		if(systemAdminRepo.findOneByUsername(patient.getUsername()) != null) {
			System.out.println("SystemAdmin sa datim Username-om vec postoji.");
			return null;
		}
		
		if(pharmacyAdminRepo.findOneByUsername(patient.getUsername()) != null) {
			System.out.println("PharmacyAdmin sa datim Username-om vec postoji.");
			return null;
		}
		
		if(dermatologistRepo.findOneByUsername(patient.getUsername()) != null) {
			System.out.println("Dermatologist sa datim Username-om vec postoji.");
			return null;
		}
		
		if(pharmacistRepo.findOneByUsername(patient.getUsername()) != null) {
			System.out.println("Pharmacist sa datim Username-om vec postoji.");
			return null;
		}
		
		if(supplierRepo.findOneByUsername(patient.getUsername()) != null) {
			System.out.println("Supplier sa datim Username-om vec postoji.");
			return null;
		}
		
		Patient newPatient = new Patient();
		newPatient.setUsername(patient.getUsername());
		newPatient.setPassword(patient.getPassword());
		newPatient.setFirstName(patient.getFirstName());
		newPatient.setLastName(patient.getLastName());
		newPatient.setAddress(patient.getAddress());
		newPatient.setCity(patient.getCity());
		newPatient.setPhoneNumber(patient.getPhoneNumber());
		
		//authorities?
		//newPatient.setAuthorities();
		
		//patientRepo.save(newPatient);
		
		return newPatient;
		
		
	}
	
}
