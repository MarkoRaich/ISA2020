package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.DrugPriceAndPharmacyDTO;
import com.example.ISA2020.service.DrugPriceService;

@RestController
@RequestMapping(value = "/api/drugPrice", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugPriceController {
	
	@Autowired
	private DrugPriceService drugPriceService;
	
	@GetMapping(value="/getAllDrugsAndPharmacies")
	public ResponseEntity<List<DrugPriceAndPharmacyDTO>> getAllDrugPriceAndPharmacies() {
		List<DrugPriceAndPharmacyDTO> drugPriceAndPharmacies = drugPriceService.getAllDrugPriceAndPharmacyDTO();
	
		return new ResponseEntity<>(drugPriceAndPharmacies, HttpStatus.OK);
	}

}
