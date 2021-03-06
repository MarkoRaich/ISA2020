package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.ISA2020.entity.users.Patient;

public class EditPatientDTO {

	private Long id;

	@NotEmpty(message = "First name is empty.")
	@Size(message = "Max size for first name is 30.", max = 30)
    private String firstName;
    
	@NotEmpty(message = "Last name is empty.")
	@Size(message = "Max size for last name is 30.", max = 30)
    private String lastName;

    @NotEmpty(message = "Phone number is empty.")
    @Size(min = 9, max = 10)
    private String phoneNumber;
    
	@NotEmpty(message = "Address is empty.")
    private String address;
    
    

	public EditPatientDTO() {
		super();
	}

	public EditPatientDTO(Long id, String firstName, String lastName, String phoneNumber, String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public EditPatientDTO(Patient p) {
		this(p.getId(), p.getFirstName(), p.getLastName(), p.getPhoneNumber(), p.getAddress());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	

    
    
}
