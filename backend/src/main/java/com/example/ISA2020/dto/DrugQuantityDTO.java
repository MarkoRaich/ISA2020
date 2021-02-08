package com.example.ISA2020.dto;

public class DrugQuantityDTO {
	
	private String pharmacyName;
	
	private String drugName;
	
	private String drugCode;
	
	

	public DrugQuantityDTO() {
		super();
	}


	public DrugQuantityDTO(String pharmacyName, String drugName, String drugCode) {
		super();
		this.pharmacyName = pharmacyName;
		this.drugName = drugName;
		this.drugCode = drugCode;
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
	
	
}
