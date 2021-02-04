package com.example.ISA2020.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PharmacyExaminationPriceDTO {
	
	private String examinationName;
	
    private LocalDateTime startDateTime;
    
    private LocalDateTime endDateTime;
    
    private double price;

	public PharmacyExaminationPriceDTO() {
		super();
	}

	public PharmacyExaminationPriceDTO(String examinationName, LocalDateTime startDateTime, LocalDateTime endDateTime,
			double price) {
		super();
		this.examinationName = examinationName;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.price = price;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
  
    
    
}
