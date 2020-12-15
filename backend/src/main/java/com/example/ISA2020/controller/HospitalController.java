package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.HospitalDTO;
import com.example.ISA2020.entity.Hospital;
import com.example.ISA2020.service.HospitalService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/hospital")
public class HospitalController {
	
	@Autowired 
	private HospitalService hospitalService;
	
	@PostMapping(value = "/create")
    public ResponseEntity<Hospital> register(@RequestBody HospitalDTO hospitalDTO) {
        try {
            Hospital newHospital = hospitalService.createHospital(hospitalDTO);
            if (newHospital == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newHospital, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<Hospital>> getAllHospitals() {
		List<Hospital> hospitals = hospitalService.getAllHospitals();
		if(hospitals.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(hospitals, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Hospital> getOneById(@PathVariable Long id){
		Hospital hospital = hospitalService.findById(id);
		if(hospital == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(hospital, HttpStatus.OK);
	}
}