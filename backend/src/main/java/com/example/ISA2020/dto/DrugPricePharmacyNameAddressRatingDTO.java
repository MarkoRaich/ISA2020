package com.example.ISA2020.dto;


public class DrugPricePharmacyNameAddressRatingDTO {

	private String pharmacyName;
	
	private String pharmacyAddress;
	
	private double pharmacyRating;
	
	

	public DrugPricePharmacyNameAddressRatingDTO() {
		super();
	}

	
	public DrugPricePharmacyNameAddressRatingDTO(String pharmacyName, String pharmacyAddress, double pharmacyRating) {
		super();
		this.pharmacyName = pharmacyName;
		this.pharmacyAddress = pharmacyAddress;
		this.pharmacyRating = pharmacyRating;
	}


	
	public String getPharmacyName() {
		return pharmacyName;
	}


	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}


	public String getPharmacyAddress() {
		return pharmacyAddress;
	}


	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}


	public double getPharmacyRating() {
		return pharmacyRating;
	}


	public void setPharmacyRating(double pharmacyRating) {
		this.pharmacyRating = pharmacyRating;
	}

	
}
