package com.example.ISA2020.dto;

import com.example.ISA2020.entity.users.Dermatologist;


public class DermatologistDTO {

	private Long id;
	
	private String firstName;

    private String lastName;
    

    private String email;

    private String password;

    private Double rating;
	
	
	
	public DermatologistDTO() {
		super();
	}

	

	public DermatologistDTO(Long id, String firstName, String lastName,
			String email, String password, Double rating) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.rating = rating;
	}



	public DermatologistDTO(Dermatologist dermatologist) {
		this(dermatologist.getId(),
			 dermatologist.getFirstName(),
			 dermatologist.getLastName(),
			 dermatologist.getUsername(),
			 dermatologist.getPassword(),
			 dermatologist.getRating() );
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Double getRating() {
		return rating;
	}



	public void setRating(Double rating) {
		this.rating = rating;
	}

	
	
	
}
