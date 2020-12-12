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

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.dto.DrugNameAndCodeDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.service.DrugService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/drug")
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
	public ResponseEntity<List<Drug>> getAllDrugs() {
		List<Drug> drugs = drugService.getAllDrugs();
	
		return new ResponseEntity<>(drugs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Drug> getOneById(@PathVariable Long id){
		Drug drug = drugService.findById(id);
		if(drug == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(drug, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByCode/{code}")
	public ResponseEntity<Drug> getOneByCode(@PathVariable String code){
		Drug drug = drugService.findByCode(code);
		if(drug == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(drug, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getByCodeInfo/{code}")
	public ResponseEntity<DrugNameAndCodeDTO> getOneByCodeInfo(@PathVariable String code){
		Drug drug = drugService.findByCode(code);
		DrugNameAndCodeDTO drugDTO = new DrugNameAndCodeDTO(drug.getName(), drug.getCode());
		if(drugDTO == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(drugDTO, HttpStatus.OK);
	}
}
