package com.example.ISA2020.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.EditPharmAdminDTO;
import com.example.ISA2020.dto.PharmacyAdminDTO;
import com.example.ISA2020.service.PharmacyAdminService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping(value = "/api/pharmacy-admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyAdminController {

	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	
	
	
	@GetMapping(value = "/all")
    //@PreAuthorize("hasRole('CLINICAL_CENTRE_ADMIN')")
	public ResponseEntity<List<PharmacyAdminDTO>> getAllPharmacyAdminsForPharmacy(@RequestParam Long pharmId) {
		return new ResponseEntity<>(pharmacyAdminService.getAllPharmacyAdminsForPharmacy(pharmId), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public EditPharmAdminDTO getPharmacyAdmin(@PathVariable Long id) {
		
		return pharmacyAdminService.getPharmacyAdmin(id);
	}
	
	
	 @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	 public ResponseEntity<PharmacyAdminDTO> editPersonalInformation(@Valid @RequestBody EditPharmAdminDTO pharmacyAdminDTO) {
		 
		 
		 PharmacyAdminDTO pharmAdminDTO = pharmacyAdminService.editPersonalInformation(pharmacyAdminDTO);
	        if (pharmAdminDTO == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>(pharmAdminDTO, HttpStatus.CREATED);
	    }
	
	
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	 //@PreAuthorize("hasRole('SYSTEM_ADMIN')")
	 public ResponseEntity<PharmacyAdminDTO> addPharmacyAdmin(@Valid @RequestBody PharmacyAdminDTO pharmacyAdminDTO) {
		 PharmacyAdminDTO pharmAdminDTO = pharmacyAdminService.create(pharmacyAdminDTO);
	        if (pharmAdminDTO == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<>(pharmAdminDTO, HttpStatus.CREATED);
	    }
	
}
