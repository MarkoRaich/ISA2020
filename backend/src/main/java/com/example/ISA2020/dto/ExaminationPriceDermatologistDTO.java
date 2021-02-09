package com.example.ISA2020.dto;

import java.time.LocalDateTime;

public class ExaminationPriceDermatologistDTO {
	
	private Long examinationId;
	
	private String examinationName;
	
	private String dermatologistName;
	
	private double price;
	
    private LocalDateTime startDateTime;
    
    private LocalDateTime endDateTime;
    
    private double dermatologistRating;
    
    

	public ExaminationPriceDermatologistDTO() {
		super();
	}

	public ExaminationPriceDermatologistDTO(Long examinationId, String examinationName, String dermatologistName, double price,
			LocalDateTime startDateTime, LocalDateTime endDateTime, double dermatologistRating) {
		super();
		this.examinationName = examinationName;
		this.dermatologistName = dermatologistName;
		this.price = price;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.dermatologistRating = dermatologistRating;
		this.examinationId = examinationId;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public String getDermatologistName() {
		return dermatologistName;
	}

	public void setDermatologistName(String dermatologistName) {
		this.dermatologistName = dermatologistName;
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

	public double getDermatologistRating() {
		return dermatologistRating;
	}

	public void setDermatologistRating(double dermatologistRating) {
		this.dermatologistRating = dermatologistRating;
	}

	public Long getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}
    
	
    
}	
