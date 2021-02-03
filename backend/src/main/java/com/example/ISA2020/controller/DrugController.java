package com.example.ISA2020.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.service.DrugService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/drug")
public class DrugController {
	
	@Autowired 
	private DrugService drugService;

	
	@PostMapping(value = "/create")
    public ResponseEntity<Drug> create(@RequestBody DrugDTO drugDTO) {
        try {
            Drug newDrug = drugService.createDrug(drugDTO);
            if (newDrug == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newDrug, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<DrugDTO>> getAllDrugs() throws IOException {
		List<DrugDTO> drugsDTO = drugService.getAllDrugs();
		
		return new ResponseEntity<>(drugsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Drug> getOneById(@PathVariable Long id) throws Exception{
		Drug drug = drugService.findById(id);
		if(drug == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		

		return new ResponseEntity<>(drug, HttpStatus.OK);
	}
	
	

	
	
}