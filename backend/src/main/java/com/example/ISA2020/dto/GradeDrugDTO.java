package com.example.ISA2020.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class GradeDrugDTO {
	
    private Long id;
	
    private double grade;
    
    private String drugName;
    
    private String drugCode;
    
    

	public GradeDrugDTO() {
		super();
	}



	public GradeDrugDTO(Long id, double grade, String drugName, String drugCode) {
		super();
		this.id = id;
		this.grade = grade;
		this.drugName = drugName;
		this.drugCode = drugCode;
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



	public String getDrugName() {
		return drugName;
	}



	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}



	public String getDrugCode() {
		return drugCode;
	}



	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
    
	
	
    

}
