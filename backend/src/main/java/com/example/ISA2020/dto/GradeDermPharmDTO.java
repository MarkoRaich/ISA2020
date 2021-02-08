package com.example.ISA2020.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class GradeDermPharmDTO {
	
    private Long id;

    private double grade;
    
    private String firstName;
    
    private String lastName;

	public GradeDermPharmDTO() {
		super();
	}

	public GradeDermPharmDTO(Long id, double grade, String firstName, String lastName) {
		super();
		this.id = id;
		this.grade = grade;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
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
    
    
    
}
