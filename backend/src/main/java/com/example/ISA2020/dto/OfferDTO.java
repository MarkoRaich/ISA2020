package com.example.ISA2020.dto;

import java.util.Set;

import com.example.ISA2020.entity.SupplierOffer;

public class OfferDTO {

	private Long id;
	
	private double offer;
	
	private String deliveryDeadline;
	
	private SupplierDTO supplier;
	
	private String status;
	
	
	

	public OfferDTO() {
		super();
	}

	public OfferDTO(Long id, double offer, String deliveryDeadline, SupplierDTO supplier, String status) {
		super();
		this.id = id;
		this.offer = offer;
		this.deliveryDeadline = deliveryDeadline;
		this.supplier = supplier;
		this.status = status;
	}

	public OfferDTO(SupplierOffer offer) {
		this.id = offer.getId();
		this.offer = offer.getOffer();
		this.deliveryDeadline = offer.getDeliveryDeadline().toString();
		this.supplier = new SupplierDTO(offer.getSupplier());
		this.status = offer.getStatus().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public String getDeliveryDeadline() {
		return deliveryDeadline;
	}

	public void setDeliveryDeadline(String deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}

	public SupplierDTO getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
