package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.service.PatientService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Patient>> getAll() {
		List<Patient> patients = patientService.getAllPatients();
		if(patients == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Patient> getOne(@PathVariable Long id) {
		Patient patient = patientService.findById(id);
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}
	
	
	

}
