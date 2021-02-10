package com.example.ISA2020.dto;

import java.time.LocalDateTime;

public class ConsultationPriceAddressDTO {
	
	private Long consultationId;
	
	private String consultationName;
	
	private String pharmacyName;
	
	private double price;
	
	private double pharmacyRating;
	
    private LocalDateTime startDateTime;
    
    private LocalDateTime endDateTime;
    
    private String pharmacyAddress;
    
    private String status;

    
    
	public ConsultationPriceAddressDTO() {
		super();
	}

	public ConsultationPriceAddressDTO(Long consultationId, String consultationName, String pharmacyName, double price,
			double pharmacyRating, LocalDateTime startDateTime, LocalDateTime endDateTime, String pharmacyAddress, String status) {
		super();
		this.consultationName = consultationName;
		this.pharmacyName = pharmacyName;
		this.price = price;
		this.pharmacyRating = pharmacyRating;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.pharmacyAddress = pharmacyAddress;
		this.consultationId = consultationId;
		this.status = status;
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

	public double getPharmacyRating() {
		return pharmacyRating;
	}

	public void setPharmacyRating(double pharmacyRating) {
		this.pharmacyRating = pharmacyRating;
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

	public String getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}

	public Long getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(Long consultationId) {
		this.consultationId = consultationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


    
    
}
