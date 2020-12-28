package com.example.ISA2020.dto;

import com.example.ISA2020.entity.Pharmacy;


public class PharmacyDTO {
	
	private Long id;

	private String name;
	
	private String address;
	
	/* private String apiKey; */

	
	
	public PharmacyDTO() {
		super();
	}


	public PharmacyDTO(Long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		/* this.apiKey = apiKey; */
	}
	
	public PharmacyDTO(String name, String address) {
		this.name = name;
		this.address = address;
		/* this.apiKey = apiKey; */
	}
	
	public PharmacyDTO(String name) {
		this.name = name;
	}
	
	
	public PharmacyDTO(Pharmacy p) {
		this(p.getId(), p.getName(), p.getAddress());
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


	
	
	
	
}
