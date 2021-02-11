package com.example.ISA2020.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.AvailableExaminationDTO;
import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.ExaminationService;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.service.PharmacyService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "api/examination", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExaminationController {
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	@Autowired
	private ExaminationService examinationService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	
	@GetMapping(value ="/forAdmin")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<List<AvailableExaminationDTO>> getAllAvailableExaminationsForAdmin() {
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	       
	     return new ResponseEntity<>(examinationService.getAvailableExaminationsForPharmacy(pharmacyAdmin.getPharmacy().getId()), HttpStatus.OK);
	  
	}
	
	@GetMapping(value ="/inPharmacy")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<List<AvailableExaminationDTO>> getAvailableExaminationsInPharmacy(@RequestParam(value = "pharmId", required = true) String pharmId ){
    	
    	Pharmacy pharmacy = pharmacyService.findById(Long.valueOf(pharmId));		
	   
	     return new ResponseEntity<>(examinationService.getAvailableExaminationsForPharmacy(pharmacy.getId()), HttpStatus.OK);
	  
	}
	
	 @PostMapping(path = "/create-examination", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	 public ResponseEntity<ExaminationDTO> createAvailableExamination(@Valid @RequestBody AvailableExaminationDTO availableExaminationDTO) {
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	        ExaminationDTO createdExamination = examinationService.createAvailableExamination(availableExaminationDTO, pharmacyAdmin);
	        if (createdExamination == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<>(createdExamination, HttpStatus.CREATED);
	    }
}
