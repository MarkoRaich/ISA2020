package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ISA2020.dto.AddDermatologistDTO;
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
    
    
    @GetMapping(value = "/available") //vrati sve dermatologe koje ima u sistemu i onda se od njih bira koji se ubacuje
    //@PreAuthorize("hasAnyRole('PHARMACY_ADMIN','PHARMACIST')")
    public ResponseEntity<List<DermatologistDTO>> getAllAvailableDermatologists(){
    	
    	return  new ResponseEntity<>( dermatologistService.getAllActiveDermatologists(), HttpStatus.OK );
    	
    	 
    }
    
//    @PostMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
//    public ResponseEntity<DermatologistDTO> addDermatolog( @PathVariable("id") Long id, @Valid @RequestBody AddDermatologistDTO dermatologistDTO ) {
//    	
//    	 PharmacyAdmin pharmacyAdmin = pharmacyAdmiService.getLoginAdmin();
//         if (pharmacyAdmin == null) {
//             return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//         }
//        try {
//            DoctorDTO createdDoctor = dermatologistService.addDermatologistToPharmacy(dermatologistDTO, pharmacyAdmin);
//            if (createdDoctor == null) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//            return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
//        } catch (DateTimeParseException ex) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
    
    
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
