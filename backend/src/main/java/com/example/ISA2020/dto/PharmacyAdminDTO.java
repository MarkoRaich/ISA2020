package com.example.ISA2020.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.ISA2020.entity.users.PharmacyAdmin;

public class PharmacyAdminDTO {
	
	private Long id;
	
	@NotEmpty(message = "Email is empty.")
    @Email(message = "Email is invalid.")
	private String email;
	
	@NotEmpty(message = "First name is empty.")
	@Size(message = "Max size for first name is 30.", max = 30)
	private String firstName;
	
	@NotEmpty(message = "Last name is empty.")
    @Size(message = "Max size for last name is 30.", max = 30)
	private String lastName;
	
	@NotEmpty(message = "Phone number is empty.")
    @Size(min = 9, max = 10)
  //@Pattern(regexp = "0[0-9]+")
	private String phoneNumber;
	
	@NotNull
	private PharmacyDTO pharmacy;
	
	
	public PharmacyAdminDTO() {}


	public PharmacyAdminDTO(Long id,
			@NotEmpty(message = "Email is empty.") @Email(message = "Email is invalid.") String email,
			@NotEmpty(message = "First name is empty.") @Size(message = "Max size for first name is 30.", max = 30) String firstName,
			@NotEmpty(message = "Last name is empty.") @Size(message = "Max size for last name is 30.", max = 30) String lastName,
			@NotEmpty(message = "Phone number is empty.") @Size(min = 9, max = 10) String phoneNumber) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public PharmacyAdminDTO(PharmacyAdmin pharmacyAdmin) {
		this(pharmacyAdmin.getId(),
			 pharmacyAdmin.getUsername(), 
			 pharmacyAdmin.getFirstName(), 
			 pharmacyAdmin.getLastName(),
			 pharmacyAdmin.getPhoneNumber()
				);	 
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public PharmacyDTO getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(PharmacyDTO pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	

}
