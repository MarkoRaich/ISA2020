package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.EditPharmacyDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.Hospital;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.service.PharmacyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/noAuth/pharmacy")
public class PharmacyController {
	
	@Autowired 
	private PharmacyService pharmacyService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	@PostMapping(value = "/create")
    public ResponseEntity<Pharmacy> create(@RequestBody PharmacyDTO pharmacyDTO) {
        try {
            Pharmacy newPharmacy = pharmacyService.createPharmacy(pharmacyDTO);
            if (newPharmacy == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newPharmacy, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<Pharmacy>> getAllPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
	
		return new ResponseEntity<>(pharmacies, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pharmacy> getOneById(@PathVariable Long id){
		Pharmacy pharmacy = pharmacyService.findById(id);
		if(pharmacy == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pharmacy, HttpStatus.OK);
	}
	
	//------------------------------------------------------------------------
	
	@GetMapping(value = "/pharmacy-info")
    //@PreAuthorize("hasAnyRole('PHARMACY_ADMIN')
	public ResponseEntity<PharmacyDTO> getPharmacyOfPharmacyAdmin() {
		
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
		if(pharmacyAdmin == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(new PharmacyDTO(pharmacyAdmin.getPharmacy()), HttpStatus.OK);
		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<EditPharmacyDTO> edit(@Valid @RequestBody EditPharmacyDTO pharmacyDTO) {
		
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        EditPharmacyDTO changedPharmacy = pharmacyService.edit(pharmacyDTO, pharmacyAdmin.getPharmacy().getId());
        if (changedPharmacy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(changedPharmacy, HttpStatus.ACCEPTED);
    }
	
	
	
}
