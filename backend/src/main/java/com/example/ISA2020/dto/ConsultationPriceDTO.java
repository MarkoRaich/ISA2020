package com.example.ISA2020.dto;

import java.time.LocalDateTime;

public class ConsultationPriceDTO {
	
	private String consultationName;
	
	private String pharmacyName;
	
	private double price;
	
    private LocalDateTime startDateTime;
    
    private LocalDateTime endDateTime;

	public ConsultationPriceDTO() {
		super();
	}

	public ConsultationPriceDTO(String consultationName, String pharmacyName, double price, LocalDateTime startDateTime,
			LocalDateTime endDateTime) {
		super();
		this.consultationName = consultationName;
		this.pharmacyName = pharmacyName;
		this.price = price;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public String getConsultationName() {
		return consultationName;
	}

	public void setConsultationName(String consultationName) {
		this.consultationName = consultationName;
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
    
    
    
}
