package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;

import com.example.ISA2020.entity.users.Patient;

public class PatientDTO {
	
	//username je u stvari email
    @NotEmpty(message = "Username is empty.")
    private String username;

    @NotEmpty(message = "Password is empty.")
    private String password;
    
    @NotEmpty(message = "FirstName is empty.")
    private String firstName;
    
    @NotEmpty(message = "LastName is empty.")
    private String lastName;
    
    @NotEmpty(message = "PhoneNumber is empty.") 
    private String phoneNumber;
    
    
    @NotEmpty(message = "Address is empty.")
    private String address;
    
    @NotEmpty(message = "City is empty.")
    private String city;
    
    @NotEmpty(message = "Points is empty.")
    private int points;
    
    @NotEmpty(message = "Penalties is empty.")
    private int penalties;
    
    

	public PatientDTO() {
		super();
	}

	public PatientDTO(String username, String password, String firstName, String lastName, String phoneNumber, String address, String city,
			int points, int penalties) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.points = points;
		this.penalties = penalties;
	}
	
	public PatientDTO(Patient patient) {
		this(patient.getUsername(), patient.getPassword(), patient.getFirstName(), patient.getLastName(), patient.getPhoneNumber(), patient.getAddress(),
				patient.getCity(), patient.getPoints(), patient.getPenalties());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
