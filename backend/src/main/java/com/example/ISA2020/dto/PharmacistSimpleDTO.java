package com.example.ISA2020.dto;

public class PharmacistSimpleDTO {
	
	private Long pharmacistId;
	
	private String firstName;
	
	private String lastName;
	
	private double rating;
	
	private Long pharmacyId;
	
	

	public PharmacistSimpleDTO() {
		super();
	}

	public PharmacistSimpleDTO(Long pharmacistId, String firstName, String lastName, double rating, Long pharmacyId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
		this.pharmacistId = pharmacistId;
		this.pharmacyId = pharmacyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Long getPharmacistId() {
		return pharmacistId;
	}

	public void setPharmacistId(Long pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
	
	
}
