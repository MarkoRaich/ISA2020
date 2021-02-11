package com.example.ISA2020.controller;

import java.time.format.DateTimeParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ISA2020.dto.DermatologistDTO;
import com.example.ISA2020.dto.PharmacistDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.DermatologistService;
import com.example.ISA2020.service.PharmacyAdminService;


@RestController
@RequestMapping(value = "/api/dermatologist")
public class DermatologistController {
	
	@Autowired
	private DermatologistService dermatologistService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	
	
    @GetMapping(value = "/all")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<List<DermatologistDTO>> getAllDermatologistsForAdmin() {
    	
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(dermatologistService.findAllDermatologistsInPharmacy(pharmacyAdmin.getPharmacy()), HttpStatus.OK);
    }
    
    @GetMapping(value = "/available") 										//vrati sve dermatologe slobodne za ove parametre za kreiranje termina pregleda
    //@PreAuthorize("hasAnyRole('PHARMACY_ADMIN','PHARMACIST')")
    public ResponseEntity<List<DermatologistDTO>> getAllAvailableDermatologists(@RequestParam(value = "startDateTime", required = true) String startDateTime,
																		        @RequestParam(value = "endDateTime", required = true) String endDateTime){
    	
    	System.out.println(startDateTime + " | " + endDateTime);
    	//System.out.println("pogodio dobavljanje slobodnih dermatologa ");
    	PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(dermatologistService.getAllAvailableDermatologists(pharmacyAdmin.getPharmacy(), startDateTime, endDateTime), HttpStatus.OK);
    }
    
    @GetMapping(value = "/other")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<List<DermatologistDTO>> getOtherDermatologistsForAdmin() {
    	
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(dermatologistService.findAllDermatologistsNotInPharmacy(pharmacyAdmin.getPharmacy()), HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/search")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<List<DermatologistDTO>> searchDermatologistsInPharmacy(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName ) {
		 
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
        return new ResponseEntity<>(dermatologistService.searchDermatologistsInPharmacy(pharmacyAdmin.getPharmacy().getId(), firstName, lastName), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<DermatologistDTO> addDermatologToPharmacy(@Valid @RequestBody DermatologistDTO dermatologistDTO ) {
	  	
    	
	  	 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	       if (pharmacyAdmin == null) {
	           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	       }
	      try {
	    	  DermatologistDTO derm = dermatologistService.addDermatologistToPharmacy(dermatologistDTO, pharmacyAdmin);
	          if (derm == null) {
	        	 
	              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	          }
	          return new ResponseEntity<>(derm, HttpStatus.CREATED);
	          
	      } catch (DateTimeParseException ex) {
	    	  System.out.println("esi ovo vratio a");
	          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	      }
	  }
    
    
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<DermatologistDTO> deleteDermatologistFromAdminPharmacy(@PathVariable("id") Long id) {
    	
    	PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        DermatologistDTO dermatologistDTO = dermatologistService.deleteDermatologistFromPharmacy(pharmacyAdmin.getPharmacy().getId(), id);
        if (dermatologistDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        System.out.println("brise se " + dermatologistDTO.getEmail());
        return new ResponseEntity<>(dermatologistDTO, HttpStatus.ACCEPTED);
    }
    
    
   
    
    
	
  
    
    
}
