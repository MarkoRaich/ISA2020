package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.dto.ExaminationTypeDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.ExaminationTypeService;
import com.example.ISA2020.service.PharmacyAdminService;

@RestController
@RequestMapping(value = "/api/examination-type")
public class ExaminationTypeController {

	
	@Autowired
	private ExaminationTypeService examinationTypeService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	
	
	 @GetMapping(value = "/all")
	 //@PreAuthorize("hasAnyRole('PHARAMCY_ADMIN')")
	 public ResponseEntity<List<ExaminationTypeDTO>> getAllExaminationTypesForAdmin() {
		 
		  PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	            return new ResponseEntity<>(examinationTypeService.findAllTypesInClinic(pharmacyAdmin.getPharmacy().getId()), HttpStatus.OK);
	 }



	
}
