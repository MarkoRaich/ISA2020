package com.example.ISA2020.controller;

import java.time.format.DateTimeParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.PharmacistDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.PharmacistService;
import com.example.ISA2020.service.PharmacyAdminService;

@RestController
@RequestMapping(value = "/api/pharmacist")
public class PharmacistController {

	@Autowired
	private PharmacistService pharmacistService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	
	@GetMapping(value = "/all")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<List<PharmacistDTO>> getAllPharmacistsForAdmin() {
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	      
	     return new ResponseEntity<>(pharmacistService.findAllPharmacistsInPharmacy(pharmacyAdmin.getPharmacy().getId()), HttpStatus.OK);
	     
	}
	
	@GetMapping(value = "/search")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<List<PharmacistDTO>> searchDoctorsInClinic(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName ) {
		 
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
        return new ResponseEntity<>(pharmacistService.searchPharmacistsInPharmacy(pharmacyAdmin.getPharmacy().getId(), firstName, lastName), HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//PreAuthorize("hasRole('PHAMRACY_ADMIN')")
	public ResponseEntity<PharmacistDTO> createPharmacist(@Valid @RequestBody PharmacistDTO pharmacistDTO){
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
        
	     try {
        	PharmacistDTO pharmacist = pharmacistService.create(pharmacistDTO, pharmacyAdmin.getPharmacy());
            if (pharmacist == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(pharmacist, HttpStatus.CREATED);
         } catch (DateTimeParseException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}
	
	
	
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<PharmacistDTO> deleteDoctor(@PathVariable("id") Long id) {
    	
    	PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        PharmacistDTO pharmacistDTO = pharmacistService.deletePharmacist(pharmacyAdmin.getPharmacy().getId(), id);
        if (pharmacistDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(pharmacistDTO, HttpStatus.ACCEPTED);
    }
	
	
	
}
