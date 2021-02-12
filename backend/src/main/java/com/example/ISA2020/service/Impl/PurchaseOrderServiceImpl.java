package com.example.ISA2020.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.PurchaseOrderDTO;
import com.example.ISA2020.dto.PurchaseOrderItemDTO;

import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.PurchaseOrder;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.entity.PurchaseOrderItem;
import com.example.ISA2020.enumeration.PurchaseOrderStatus;
import com.example.ISA2020.repository.PurchaseOrderRepository;
import com.example.ISA2020.service.DrugService;
import com.example.ISA2020.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService  {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private DrugService drugService;
	
	
	@Override
	public List<PurchaseOrderDTO> getPurchaseOrdersInPharmacy(Pharmacy pharmacy) {
				
		return convertToDTO(purchaseOrderRepository.findByPharmacyId(pharmacy.getId()));
		
	}
	
	@Override
	public PurchaseOrderDTO createPurchaseOrder(@Valid PurchaseOrderDTO purchaseOrderDTO, PharmacyAdmin pharmacyAdmin) {
		
		System.out.println(purchaseOrderDTO.toString());
		
		PurchaseOrder order = new PurchaseOrder(PurchaseOrderStatus.valueOf(purchaseOrderDTO.getStatus()),
												pharmacyAdmin.getPharmacy(),
												convertFromDTO(purchaseOrderDTO.getOrderitems()),
												convertToDT(purchaseOrderDTO.getDeadline())
				);	
		
		
		PurchaseOrderDTO temp = new PurchaseOrderDTO(purchaseOrderRepository.save(order));
		System.out.println(temp.toString());
		return temp;
	}
	
	private LocalDateTime convertToDT(String str) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(str, formatter);
}

	
	
	private Set<PurchaseOrderItem> convertFromDTO(Set<PurchaseOrderItemDTO> itemsDTO){
		 Set<PurchaseOrderItem> returnItems = new HashSet<PurchaseOrderItem>();
		for(PurchaseOrderItemDTO itemDTO: itemsDTO) {
			returnItems.add( new PurchaseOrderItem(itemDTO.getQuantity(), drugService.findById(itemDTO.getDrug().getId())));
		 }
		return returnItems;
	}
	
	
	private List<PurchaseOrderDTO> convertToDTO(List<PurchaseOrder> orders){
		
		List<PurchaseOrderDTO> ordersDTO = new ArrayList<>();
		for(PurchaseOrder order: orders) {
			ordersDTO.add(new PurchaseOrderDTO(order));
		}
		
		return ordersDTO;
	}
	
}
