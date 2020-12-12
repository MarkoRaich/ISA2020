package com.example.ISA2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Hospital;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.service.PharmacyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/pharmacy")
public class PharmacyController {
	
	@Autowired 
	private PharmacyService pharmacyService;
	
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
}
