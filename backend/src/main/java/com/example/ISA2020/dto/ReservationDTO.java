package com.example.ISA2020.dto;

import com.example.ISA2020.entity.Reservation;

public class ReservationDTO {
	
	private Long id;
	
	private String pharmacyName;
	
	private String drugName;
	
	private String drugCode;
	
	private String generatedKey;
	
	private Integer quantity;
	
	private String status;

	
	
	public ReservationDTO() {
		super();
	}

	public ReservationDTO(Long id, String pharmacyName, String drugName, String drugCode, String generatedKey, Integer quantity, String status) {
		super();
		this.pharmacyName = pharmacyName;
		this.drugName = drugName;
		this.drugCode = drugCode;
		this.generatedKey = generatedKey;
		this.quantity = quantity;
		this.id = id;
		this.status = status;
	}

	public ReservationDTO(Reservation reservation) {
		super();
		this.id = reservation.getId();
		this.pharmacyName = reservation.getPharmacy().getName();
		this.drugName = reservation.getDrug().getName();
		this.drugCode = reservation.getDrug().getCode();
		this.generatedKey = reservation.getGeneratedKey();
		this.quantity = reservation.getQuantity();
		this.status = reservation.getStatus().toString();
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getGeneratedKey() {
		return generatedKey;
	}

	public void setGeneratedKey(String generatedKey) {
		this.generatedKey = generatedKey;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	
	
}
