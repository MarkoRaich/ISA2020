package com.example.ISA2020.dto;

import com.example.ISA2020.entity.PurchaseOrderItem;

public class PurchaseOrderItemDTO {

	private Long id;
	
	private int quantity;
	
	private DrugDTO drug;

	public PurchaseOrderItemDTO(Long id, int quantity, DrugDTO drug) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.drug = drug;
	}

	public PurchaseOrderItemDTO() {
		super();
	}

	public PurchaseOrderItemDTO(PurchaseOrderItem item) {
		this(item.getId(),item.getQuantity(),new DrugDTO(item.getDrug()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public DrugDTO getDrug() {
		return drug;
	}

	public void setDrug(DrugDTO drug) {
		this.drug = drug;
	}
	
	
	
}
