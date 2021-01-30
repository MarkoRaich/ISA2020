package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.ISA2020.entity.Pharmacy;


public class PharmacyDTO {
	
	private Long id;

	@NotEmpty(message = "Name is empty.")
	@Size(message = "Max size for name is 50.", max = 50)
	private String name;
	
	@NotEmpty(message = "Address is empty.")
	private String address;
	
	
	@NotEmpty(message = "Description is empty.")
    private String description;
	
	private double pharmacyRating;

	
	
	public PharmacyDTO() {}



	
	public PharmacyDTO(Long id,
			@NotEmpty(message = "Name is empty.") @Size(message = "Max size for name is 50.", max = 50) String name,
			@NotEmpty(message = "Address is empty.") String address,
			@NotEmpty(message = "Description is empty.") String description, double pharmacyRating) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.pharmacyRating = pharmacyRating;
	}

	


	public PharmacyDTO(Long id,
			@NotEmpty(message = "Name is empty.") @Size(message = "Max size for name is 50.", max = 50) String name,
			@NotEmpty(message = "Address is empty.") String address,
			@NotEmpty(message = "Description is empty.") String description) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
	}




	public PharmacyDTO(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public PharmacyDTO(String name) {
		this.name = name;
	}
	
	
	public PharmacyDTO(Pharmacy pharmacy) {
		this(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress(), pharmacy.getDescription(), pharmacy.getRating());
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public double getPharmacyRating() {
		return pharmacyRating;
	}




	public void setPharmacyRating(double pharmacyRating) {
		this.pharmacyRating = pharmacyRating;
	}
	


	
}
