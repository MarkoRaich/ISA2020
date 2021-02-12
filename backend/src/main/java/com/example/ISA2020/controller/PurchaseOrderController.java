package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.AvailableExaminationDTO;
import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.dto.PurchaseOrderDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.service.PurchaseOrderService;

@RestController
@RequestMapping(value = "api/purchase-order", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	
	
	
	@GetMapping(value="all")
	public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrdersForAdmin() {
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	        
	     return new ResponseEntity<>(purchaseOrderService.getPurchaseOrdersInPharmacy(pharmacyAdmin.getPharmacy()), HttpStatus.OK); 
		
	}
	
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
		
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	     
	        PurchaseOrderDTO createdOrder = purchaseOrderService.createPurchaseOrder(purchaseOrderDTO, pharmacyAdmin);
	        if (createdOrder == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}
	
	
	
	
	
}
