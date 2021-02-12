package com.example.ISA2020.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.AvailableExaminationDTO;
import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.PatientService;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.service.PromotionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/promotion")
public class PromotionController {
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<PromotionDTO> createPromotion(@Valid @RequestBody PromotionDTO promotionDTO) {
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	    PromotionDTO createdPromotion = promotionService.createPromotionSendEmail(promotionDTO, pharmacyAdmin);
	        if (createdPromotion == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<>(createdPromotion, HttpStatus.CREATED);
		
	}
}
