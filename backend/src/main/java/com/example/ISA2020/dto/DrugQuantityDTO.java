package com.example.ISA2020.dto;

public class DrugQuantityDTO {
	
	private Long drugId;
	
	private Long pharmacyId;
	
	private String pharmacyName;
	
	private String drugName;
	
	private String drugCode;
	
	

	public DrugQuantityDTO() {
		super();
	}




	public DrugQuantityDTO(Long drugId, Long pharmacyId, String pharmacyName, String drugName, String drugCode) {
		super();
		this.drugId = drugId;
		this.pharmacyId = pharmacyId;
		this.pharmacyName = pharmacyName;
		this.drugName = drugName;
		this.drugCode = drugCode;
	}




	public Long getDrugId() {
		return drugId;
	}




	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}




	public Long getPharmacyId() {
		return pharmacyId;
	}




	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
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
