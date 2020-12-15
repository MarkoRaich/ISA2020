package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;

import com.example.ISA2020.entity.users.NormalUser;

public class NormalUserDTO {
	
	private Long id;
	
	@NotEmpty(message = "Username is empty.")
    private String username;

	@NotEmpty(message = "Password is empty.")
    private String password;

	@NotEmpty(message = "Firstname is empty.")
    private String firstName;

	@NotEmpty(message = "Lastname is empty.")
    private String lastName;

	@NotEmpty(message = "Email is empty.")
    private String email;

	@NotEmpty(message = "Country is empty.")
    private String country;

	@NotEmpty(message = "Phone number is empty.")
    private String phoneNumber;

	@NotEmpty(message = "Address is empty.")
    private String address;

	@NotEmpty(message = "City is empty.")
    private String city;
	

	
    public NormalUserDTO() {
    	
    }

	public NormalUserDTO(Long id, String username, String password, String firstName, String lastName, String email,
			String country, String phoneNumber, String address, String city) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;

	}

	public NormalUserDTO(NormalUser normalUser) {
    	this(normalUser.getId(), normalUser.getUsername(), normalUser.getPassword(),
				normalUser.getFirstName(), normalUser.getLastName(), normalUser.getEmail(),
				normalUser.getCountry(), normalUser.getPhoneNumber(), normalUser.getAddress(),
				normalUser.getCity());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

}

