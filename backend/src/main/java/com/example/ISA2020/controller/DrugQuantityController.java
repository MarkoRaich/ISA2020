package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.DrugQuantityService;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.PharmacistDTO;

@RestController
@RequestMapping(value = "/api/drug-quantity")
public class DrugQuantityController {
	
	@Autowired
	private DrugQuantityService drugQuantityService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	
	@GetMapping(value = "/all")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<List<DrugSearchDTO>> getAllDrugsforAdmin() {
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	
	     return new ResponseEntity<>(drugQuantityService.findAllDrugsInPharmacy(pharmacyAdmin.getPharmacy().getId()), HttpStatus.OK); 
	}
	
	@DeleteMapping("/{id}")
	//PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<DrugSearchDTO> deleteDrugFromPharmacy(@PathVariable("id") Long id){
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	     DrugSearchDTO drugDTO = drugQuantityService.deleteDrugFromPharmacy(pharmacyAdmin.getPharmacy().getId(), id);
	     if (drugDTO == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	        }
	        return new ResponseEntity<>(drugDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/search")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<List<DrugSearchDTO>> searchDrugsInPharmacy(@RequestParam(value = "name") String name ) {
		 
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
        return new ResponseEntity<>(drugQuantityService.searchDrugsInPharmacy(pharmacyAdmin.getPharmacy().getId(), name), HttpStatus.OK);
    }
	
}
