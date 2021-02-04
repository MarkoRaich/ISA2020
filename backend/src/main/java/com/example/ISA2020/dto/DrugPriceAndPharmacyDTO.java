package com.example.ISA2020.dto;


public class DrugPriceAndPharmacyDTO {
	
	private String drugName;
	
	private String drugCode;

	private String pharmacyName;

	private double price;

	
	public DrugPriceAndPharmacyDTO() {
		super();
	}
	
	public DrugPriceAndPharmacyDTO(String drugName, String drugCode, String pharmacyName, double price) {
		super();
		this.drugName = drugName;
		this.drugCode = drugCode;
		this.pharmacyName = pharmacyName;
		this.price = price;
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

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

		
	
}
