package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.AvailableExaminationDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.dto.PurchaseOrderDTO;
import com.example.ISA2020.dto.PurchaseOrderItemDTO;
import com.example.ISA2020.entity.PurchaseOrder;
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
		
//		System.out.println("Narudzbenica: " + purchaseOrderDTO);
//		for(PurchaseOrderItemDTO item : purchaseOrderDTO.getOrderitems()) {
//			System.out.println(item.getDrug().getName() + " - " + item.getQuantity());
//			
//		}
		
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
	
	
	@GetMapping(value="/accept-offer")
	//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<PurchaseOrderDTO> acceptOffer(@RequestParam(value = "orderId") String orderId, @RequestParam(value = "offerId") String offerId) {
		
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        Long idOrder = Long.parseLong(orderId);
        
        PurchaseOrder order = this.purchaseOrderService.findOneById(idOrder);
        
        if(pharmacyAdmin.getId() != order.getPharmacyAdmin().getId()) {		
        	 return new ResponseEntity<>(HttpStatus.FORBIDDEN); 		//nije ovaj admin napravio narudzbenicu i ne moze da odabere ponudu
        }

        Long idOffer = Long.parseLong(offerId);
        
        return new ResponseEntity<PurchaseOrderDTO>(purchaseOrderService.acceptOffer(order, idOffer), HttpStatus.OK);
        
	}
	
	
	@DeleteMapping("/{id}")
	//PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<PurchaseOrderDTO> deletePurchaseOrder(@PathVariable("id") Long id){
		
		 PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
	        if (pharmacyAdmin == null) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	        }
	        PurchaseOrderDTO orderDTO = purchaseOrderService.deletePurchaseOrder(pharmacyAdmin.getPharmacy().getId(), id);
	     if (orderDTO == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	        }
	        return new ResponseEntity<>(orderDTO, HttpStatus.ACCEPTED);
	}
	
}
