package com.example.ISA2020.dto;

import com.example.ISA2020.entity.Pharmacy;


public class PharmacyDTO {
	
	private Long id;

	private String name;
	
	/* private String apiKey; */

	
	
	public PharmacyDTO() {
		super();
	}


	public PharmacyDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		/* this.apiKey = apiKey; */
	}
	
	public PharmacyDTO(String name) {
		this.name = name;
		/* this.apiKey = apiKey; */
	}
	
	public PharmacyDTO(Pharmacy p) {
		this(p.getId(), p.getName());
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


	/*
	 * public String getApiKey() { return apiKey; }
	 * 
	 * 
	 * public void setApiKey(String apiKey) { this.apiKey = apiKey; }
	 */
	
	
	
}
