package com.example.ISA2020.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.ISA2020.entity.PurchaseOrder;
import com.example.ISA2020.entity.PurchaseOrderItem;

public class PurchaseOrderDTO {

	private Long id;
	
	private String status;
	
	private PharmacyDTO pharmacy;
	
	private Set<PurchaseOrderItemDTO> orderitems;
	
	private String deadline;

	
	
	
	public PurchaseOrderDTO() {
		super();
	}

	public PurchaseOrderDTO(Long id, String status, PharmacyDTO pharmacy, Set<PurchaseOrderItemDTO> orderitems,
			String deadline) {
		super();
		this.id = id;
		this.status = status;
		this.pharmacy = pharmacy;
		this.orderitems = orderitems;
		this.deadline = deadline;
	}

	public PurchaseOrderDTO(PurchaseOrder order) {
		this(order.getId(),
			 order.getStatus().toString(),
			 new PharmacyDTO(order.getPharmacy()),
			 convertToDTO(order.getOrderItems()),
			 order.getDeadline().toString()	);
	}

	private static Set<PurchaseOrderItemDTO> convertToDTO(Set<PurchaseOrderItem> items) {
		Set<PurchaseOrderItemDTO> returnSet = new HashSet<PurchaseOrderItemDTO>();
		
		for(PurchaseOrderItem item: items) {
			returnSet.add(new PurchaseOrderItemDTO(item));
		}
		
		return returnSet;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PharmacyDTO getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(PharmacyDTO pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Set<PurchaseOrderItemDTO> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<PurchaseOrderItemDTO> orderitems) {
		this.orderitems = orderitems;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "PurchaseOrderDTO [id=" + id + ", status=" + status + ", pharmacy=" + pharmacy + ", orderitems="
				+ orderitems + ", deadline=" + deadline + ", getOrderitems()=" + getOrderitems() + "]";
	}
	
	
	
}
