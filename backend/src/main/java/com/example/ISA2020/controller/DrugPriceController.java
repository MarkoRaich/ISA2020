package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.DrugPriceAndPharmacyDTO;
import com.example.ISA2020.dto.DrugPriceDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.ListDrugPriceDTO;
import com.example.ISA2020.dto.ReasonDTO;
import com.example.ISA2020.dto.VacationRequestPharmDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.DrugPriceService;
import com.example.ISA2020.service.PharmacyAdminService;

@RestController
@RequestMapping(value = "/api/drugPrice", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugPriceController {
	
	@Autowired
	private DrugPriceService drugPriceService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<DrugPriceDTO>> getAllDrugPricesForPharmacy() {
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	       
	     return new ResponseEntity<>(drugPriceService.getAddDrugPricesForPharmacy(pharmacyAdmin.getPharmacy().getId()), HttpStatus.OK);
	}
	
	
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
	
	
	@PostMapping(value="/create-pricelist", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	//PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<DrugPriceDTO> createPricelist(@RequestBody ListDrugPriceDTO pricelist){
		
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	        
	        DrugPriceDTO drug = drugPriceService.createPricelist(pharmacyAdmin.getPharmacy().getId(), pricelist);
	     if (drug == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	        }
	        return new ResponseEntity<>(drug, HttpStatus.ACCEPTED);
	        
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DrugPriceDTO> changeDrugPriceInPharmacy(@Valid @RequestBody DrugPriceDTO drugDTO){
		
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	        DrugPriceDTO drugPriceDTO = drugPriceService.changeDrugPriceInPharmacy(drugDTO, pharmacyAdmin.getPharmacy());
		     if(drugPriceDTO == null) {
		    	 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		     }
		     
		   return new ResponseEntity<>(drugPriceDTO, HttpStatus.CREATED);
		
	}

}
