package com.example.ISA2020.dto;

import java.time.LocalDateTime;

public class ExaminationPriceDTO {
	
	private Long id;

	private String examinationName;
	
	private String pharmacyName;
	
	private double price;
	
    private LocalDateTime startDateTime;
    
    private LocalDateTime endDateTime;
    
    

	public ExaminationPriceDTO() {
		super();
	}



	public ExaminationPriceDTO(Long id, String examinationName, String pharmacyName, double price, LocalDateTime startDateTime,
			LocalDateTime endDateTime) {
		super();
		this.id = id;
		this.examinationName = examinationName;
		this.pharmacyName = pharmacyName;
		this.price = price;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}



	public String getExaminationName() {
		return examinationName;
	}



	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}



	public String getPharmacyName() {
		return pharmacyName;
	}



	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}



	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}



	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}



	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
    
	
    
    
    
}
