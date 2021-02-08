package com.example.ISA2020.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.ISA2020.entity.users.Dermatologist;


public class DermatologistDTO {

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
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;
    
    @NotEmpty(message = "Start work hours is empty.")
    private String workHoursFrom;

    @NotEmpty(message = "End work hours is empty.")
    private String workHoursTo;
	
	public DermatologistDTO() {}

	

	public DermatologistDTO(Long id,
			@NotEmpty(message = "Email is empty.") @Email(message = "Email is invalid.") String email,
			@NotEmpty(message = "First name is empty.") @Size(message = "Max size for first name is 30.", max = 30) String firstName,
			@NotEmpty(message = "Last name is empty.") @Size(message = "Max size for last name is 30.", max = 30) String lastName,
			@NotEmpty(message = "Phone number is empty.") @Size(min = 9, max = 10) @Pattern(regexp = "[0-9]+") String phoneNumber,
			@NotEmpty(message = "Start work hours is empty.") String workHoursFrom,
			@NotEmpty(message = "End work hours is empty.") String workHoursTo) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.workHoursFrom = workHoursFrom;
		this.workHoursTo = workHoursTo;
	}



	public DermatologistDTO(Long id, String firstName, String lastName,
			String email, String password, Double rating) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}



	public DermatologistDTO(Dermatologist dermatologist) {
		this(dermatologist.getId(),
			 dermatologist.getUsername(),
			 dermatologist.getFirstName(),
			 dermatologist.getLastName(),
			 dermatologist.getPhoneNumber());
	}



	public DermatologistDTO(Long id2, String username, String firstName2, String lastName2, String phoneNumber2) {
		// TODO Auto-generated constructor stub
	}



	public String getWorkHoursFrom() {
		return workHoursFrom;
	}



	public void setWorkHoursFrom(String workHoursFrom) {
		this.workHoursFrom = workHoursFrom;
	}



	public String getWorkHoursTo() {
		return workHoursTo;
	}



	public void setWorkHoursTo(String workHoursTo) {
		this.workHoursTo = workHoursTo;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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



	

	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	
	
	
}
