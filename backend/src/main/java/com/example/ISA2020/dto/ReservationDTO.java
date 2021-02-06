package com.example.ISA2020.dto;

public class ReservationDTO {
	
	private String pharmacyName;
	
	private String drugName;
	
	private String drugCode;
	
	private String generatedKey;

	
	
	public ReservationDTO() {
		super();
	}

	public ReservationDTO(String pharmacyName, String drugName, String drugCode, String generatedKey) {
		super();
		this.pharmacyName = pharmacyName;
		this.drugName = drugName;
		this.drugCode = drugCode;
		this.generatedKey = generatedKey;
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
	
	
	
	
	
	
	
}
