package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.service.PatientService;
import com.example.ISA2020.service.PharmacyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	
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
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<PatientDTO> editPersonalInformation(@Valid @RequestBody EditPatientDTO editPatientDTO) {
		PatientDTO patientDTO = patientService.editPersonalInformation(editPatientDTO);
		if(patientDTO == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/addAlergie")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<Patient> addAlergie(@RequestParam("drugName") String drugName) {
		Patient patient = patientService.addAlergie(drugName);
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(patient, HttpStatus.CREATED);
	}
	
	
	// 3.9 --------------------------------------------------------------------------------------------------
	@GetMapping("/getAllPharmaciesSortedByAddress")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesByAddress() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyAddress();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllPharmaciesSortedByName")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesByName() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyName();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllPharmaciesSortedByRating")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesByRating() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyRating();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	
	
	

}
