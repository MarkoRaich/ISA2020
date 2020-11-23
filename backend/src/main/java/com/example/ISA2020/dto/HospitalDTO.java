package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;

import com.example.ISA2020.entity.Hospital;

public class HospitalDTO {
	
	private Long id;

	@NotEmpty(message = "Name is empty.")
    private String name;
    
	@NotEmpty(message = "City is empty.")
    private String city;
    
	@NotEmpty(message = "Address is empty.")
    private String address;
    
    private String apiKey;

	
	
	public HospitalDTO() {
		super();
	}


	public HospitalDTO(Long id, String name, String city, String address, String apiKey) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.apiKey = apiKey;
	}
	
	public HospitalDTO(Hospital hospital) {
		this(hospital.getId(), hospital.getName(), hospital.getCity(), hospital.getAddress(), hospital.getApiKey());
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


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getApiKey() {
		return apiKey;
	}


	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	
}
