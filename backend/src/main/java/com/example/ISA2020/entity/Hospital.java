package com.example.ISA2020.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;

@Entity
public class Hospital {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null.")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "City cannot be null.")
    @Column(nullable = false)
    private String city;
    
    @NotNull(message = "Address cannot be null.")
    @Column(nullable = false)
    private String address;
    
    @Column
    private String apiKey;
    
    

	public Hospital() {
		super();
	}

	public Hospital(@NotNull(message = "Name cannot be null.") String name,
			@NotNull(message = "City cannot be null.") String city,
			@NotNull(message = "Address cannot be null.") String address) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.apiKey = randomStringGenerator();
	}
	
	
	
	// samo za potrebe testiranja
	public Hospital(Long id, @NotNull(message = "Name cannot be null.") String name,
			@NotNull(message = "City cannot be null.") String city,
			@NotNull(message = "Address cannot be null.") String address, String apiKey) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.apiKey = apiKey;
	}

	public String randomStringGenerator() {
		 
	    int length = 15;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(generatedString);
	    
	    return generatedString;
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
