package com.example.ISA2020.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.compositeKeys.KeyDrugPharmacyQuantity;
import com.example.ISA2020.service.DrugService;
import com.example.ISA2020.service.PharmacyDrugDetailsService;
import com.example.ISA2020.service.PharmacyService;

@RestController
@RequestMapping(value = "/api/noAuth/pharmacyDrugDetails")
public class PharmacyDrugDetailsController {
	
	
	@Autowired
	private PharmacyDrugDetailsService pharmacyDrugDetailsService;
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<DrugQuantity>> getAllPharmacyDrugDetails() {
		List<DrugQuantity> pharmacyDrugDetails = pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		if(pharmacyDrugDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pharmacyDrugDetails, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DrugQuantity> getOneById(@PathVariable KeyDrugPharmacyQuantity id){
		DrugQuantity pharmacyDrugDetails = pharmacyDrugDetailsService.findById(id);
		if(pharmacyDrugDetails == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pharmacyDrugDetails, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/getAllWithSameName")
	public ResponseEntity<List<DrugSearchDTO>> getAllDrugsWithSameName(@RequestParam("name") String name) {
		List<DrugQuantity> pharmacyDrugDetails = pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		if(pharmacyDrugDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<DrugSearchDTO> drugsWithSameName = new ArrayList<>();
		
		for(DrugQuantity p : pharmacyDrugDetails) {
			if(p.getDrug().getName().toLowerCase().contains(name.toLowerCase())) {
				DrugSearchDTO dto = new DrugSearchDTO();
				dto.setId(p.getDrug().getId());
				dto.setName(p.getDrug().getName());
				//dto.setCode(p.getDrug().getCode());
				dto.setQuantity(p.getQuantity());
				dto.setPharmacyDTO(new PharmacyDTO(pharmacyService.findById(p.getPharmacy().getId())));
				drugsWithSameName.add(dto);
			}
		}
		
		
		return new ResponseEntity<>(drugsWithSameName, HttpStatus.OK);
	}
	
	@PutMapping(value="/getAllByDrugIdAndPharmacyId")
	public ResponseEntity<DrugQuantity> getAllByDrugCode(@RequestParam("idPharmacy") String idPharmacy, 
																@RequestParam("idDrug") String idDrug, 
																@RequestParam("quantity") String quantity) {
		
		List<DrugQuantity> pharmacyDrugDetails = pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		if(pharmacyDrugDetails.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<DrugSearchDTO> drugsWithSameCode = new ArrayList<>();
		int oldQuantity = 0;
		int newQuantity = 0;
		int q = Integer.parseInt(quantity);
		
		
		for(DrugQuantity p : pharmacyDrugDetails) {
			if(p.getPharmacy().getId().toString().equals(idPharmacy)) {
				System.out.println("1");
				if(p.getDrug().getId().toString().equals(idDrug)) {
					System.out.println("2");
					if(p.getQuantity() >= q) {
						System.out.println("3");
						oldQuantity = p.getQuantity();
						newQuantity = oldQuantity - q;
						p.setQuantity(newQuantity);
						pharmacyDrugDetailsService.save(p);
						return new ResponseEntity<>(p, HttpStatus.OK);
					}else {
						return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					}
				}
			}
		}
		
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
}
