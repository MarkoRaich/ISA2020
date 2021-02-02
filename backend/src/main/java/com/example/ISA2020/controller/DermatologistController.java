package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ISA2020.dto.DermatologistDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.DermatologistService;
import com.example.ISA2020.service.PharmacyAdminService;


@RestController
@RequestMapping(value = "/api/dermatologist")
public class DermatologistController {
	
	@Autowired
	private DermatologistService dermatologistService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdmiService;
	
	
	
    @GetMapping(value = "/all")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<List<DermatologistDTO>> getAllDermatologistsForAdmin() {
    	
        PharmacyAdmin pharmacyAdmin = pharmacyAdmiService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(dermatologistService.findAllDermatologistsInPharmacy(pharmacyAdmin.getPharmacy()), HttpStatus.OK);
    }

    
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<DermatologistDTO> deleteDermatologist(@PathVariable("id") Long id) {
    	
    	PharmacyAdmin pharmacyAdmin = pharmacyAdmiService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        DermatologistDTO dermatologistDTO = dermatologistService.deleteDermatologist(pharmacyAdmin.getPharmacy().getId(), id);
        if (dermatologistDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        System.out.println("brise se " + dermatologistDTO.getEmail());
        return new ResponseEntity<>(dermatologistDTO, HttpStatus.ACCEPTED);
    }
    
    
}
