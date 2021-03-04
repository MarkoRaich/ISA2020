package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.ReasonDTO;
import com.example.ISA2020.dto.VacationRequestDermDTO;
import com.example.ISA2020.dto.VacationRequestPharmDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.service.VacationRequestDermService;
import com.example.ISA2020.service.VacationRequestPharmService;

@RestController
@RequestMapping(value = "api/vacation-derm", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationRequestDermController {
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	@Autowired
	private VacationRequestDermService vacationRequestDermService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<VacationRequestDermDTO>> getAllRequestsForPharmacy(){
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	       
	     return new ResponseEntity<>(vacationRequestDermService.getAllRequestsForPharmacy(pharmacyAdmin.getPharmacy().getId()), HttpStatus.OK);
	  
	}
	
	@GetMapping("/approve/{id}")
	//PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<VacationRequestDermDTO> approveRequest(@PathVariable("id") Long id){
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	        VacationRequestDermDTO requestDTO = vacationRequestDermService.approveRequestInPharmacy(pharmacyAdmin.getPharmacy().getId(), id);
	     if (requestDTO == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	        }
	        return new ResponseEntity<>(requestDTO, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/deny/{id}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	//PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<VacationRequestDermDTO> denyRequest(@PathVariable("id") Long id, @RequestBody ReasonDTO reason){
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	        
	        VacationRequestDermDTO requestDTO = vacationRequestDermService.denyRequestInPharmacy(pharmacyAdmin.getPharmacy().getId(), id, reason.getReason());
	     if (requestDTO == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	        }
	        return new ResponseEntity<>(requestDTO, HttpStatus.ACCEPTED);
	        
	}

}