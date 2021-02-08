package com.example.ISA2020.dto;

public class GradePharmacyDTO {
	
    private Long id;

    private double grade;
    
    private String pharmacyName;
    
    private String pharmacyAddress;

	public GradePharmacyDTO() {
		super();
	}

	public GradePharmacyDTO(Long id, double grade, String pharmacyName, String pharmacyAddress) {
		super();
		this.id = id;
		this.grade = grade;
		this.pharmacyName = pharmacyName;
		this.pharmacyAddress = pharmacyAddress;
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

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}
    
    
}
