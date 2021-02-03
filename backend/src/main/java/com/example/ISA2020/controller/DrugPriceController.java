package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.DrugPriceAndPharmacyDTO;
import com.example.ISA2020.service.DrugPriceService;

@RestController
@RequestMapping(value = "/api/drugPrice", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugPriceController {
	
	@Autowired
	private DrugPriceService drugPriceService;
	
	
	//dobavlja sve lekove i apoteke
	@GetMapping(value="/getAllDrugsAndPharmacies")
	public ResponseEntity<List<DrugPriceAndPharmacyDTO>> getAllDrugPriceAndPharmacies() {
		List<DrugPriceAndPharmacyDTO> drugPriceAndPharmacies = drugPriceService.getAllDrugPriceAndPharmacyDTO();
	
		return new ResponseEntity<>(drugPriceAndPharmacies, HttpStatus.OK);
	}
	
	//dobavlja sve lekove i apoteke po NAZIVU LEKA ili samo pocetnim karakterima
	@GetMapping(value="/getAllDrugsAndPharmaciesByDrugName")
	public ResponseEntity<List<DrugPriceAndPharmacyDTO>> getAllDrugPriceAndPharmaciesByDrugName(@RequestParam("drugName") String drugName) {
		List<DrugPriceAndPharmacyDTO> drugPriceAndPharmacies = drugPriceService.getAllDrugPriceByDrugName(drugName);
	
		return new ResponseEntity<>(drugPriceAndPharmacies, HttpStatus.OK);
	}
	
	//dobavlja sve lekove i apoteke po SIFRI LEKA ili samo pocetnim karakterima
	@GetMapping(value="/getAllDrugsAndPharmaciesByDrugCode")
	public ResponseEntity<List<DrugPriceAndPharmacyDTO>> getAllDrugPriceAndPharmaciesByDrugCode(@RequestParam("drugCode") String drugCode) {
		List<DrugPriceAndPharmacyDTO> drugPriceAndPharmacies = drugPriceService.getAllDrugPriceByDrugCode(drugCode);
	
		return new ResponseEntity<>(drugPriceAndPharmacies, HttpStatus.OK);
	}
	
	//dobavlja sve lekove i apoteke po NAZIVU APOTEKE ili samo pocetnim karakterima
	@GetMapping(value="/getAllDrugsAndPharmaciesByPharmacyName")
	public ResponseEntity<List<DrugPriceAndPharmacyDTO>> getAllDrugPriceAndPharmaciesByPharmacyName(@RequestParam("pharmacyName") String pharmacyName) {
		List<DrugPriceAndPharmacyDTO> drugPriceAndPharmacies = drugPriceService.getAllDrugPriceByPharmacyName(pharmacyName);
	
		return new ResponseEntity<>(drugPriceAndPharmacies, HttpStatus.OK);
	}

}
