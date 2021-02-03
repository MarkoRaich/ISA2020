package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;

import com.example.ISA2020.entity.users.Patient;

public class PatientWithIdDTO {
	
	private Long id;
	
	private String username;

    private String firstName;
    
    private String lastName;

    private String address;

    private String city;

    private String phoneNumber;

    private int points;

    private int penalties;
    

	public PatientWithIdDTO(Long id, String username, String firstName, String lastName,
			String address, String city, String phoneNumber, int points, int penalties) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.points = points;
		this.penalties = penalties;
	}


	public PatientWithIdDTO(Patient patient) {
		this(patient.getId(), patient.getUsername(), patient.getFirstName(), patient.getLastName(), patient.getAddress(), patient.getCity(), 
				patient.getPhoneNumber(), patient.getPoints(), patient.getPenalties());
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	public int getPenalties() {
		return penalties;
	}


	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}
	
	
	
	
	
    
    
}
