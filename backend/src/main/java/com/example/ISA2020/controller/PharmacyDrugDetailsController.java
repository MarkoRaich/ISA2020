package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.entity.PharmacyDrugDetails;
import com.example.ISA2020.entity.PharmacyDrugKey;
import com.example.ISA2020.service.PharmacyDrugDetailsService;

@RestController
@RequestMapping(value = "/api/auth/pharmacyDrugDetails")
public class PharmacyDrugDetailsController {
	
	
	@Autowired
	private PharmacyDrugDetailsService pharmacyDrugDetailsService;
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<PharmacyDrugDetails>> getAllPharmacyDrugDetails() {
		List<PharmacyDrugDetails> pharmacyDrugDetails = pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		if(pharmacyDrugDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pharmacyDrugDetails, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PharmacyDrugDetails> getOneById(@PathVariable PharmacyDrugKey id){
		PharmacyDrugDetails pharmacyDrugDetails = pharmacyDrugDetailsService.findById(id);
		if(pharmacyDrugDetails == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pharmacyDrugDetails, HttpStatus.OK);
	}
}
