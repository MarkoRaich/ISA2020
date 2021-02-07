package com.example.ISA2020.service.Impl;

public class PharmacistSimpleDTO {
	
	private String firstName;
	
	private String lastName;
	
	private double rating;
	
	

	public PharmacistSimpleDTO() {
		super();
	}

	public PharmacistSimpleDTO(String firstName, String lastName, double rating) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = rating;
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
	
	
}
