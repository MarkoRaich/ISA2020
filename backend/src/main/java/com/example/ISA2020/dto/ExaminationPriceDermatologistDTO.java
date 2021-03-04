package com.example.ISA2020.dto;

import java.time.LocalDateTime;

import com.example.ISA2020.entity.Examination;

public class ExaminationPriceDermatologistDTO {
	
	private Long examinationId;
	
	private String examinationName;
	
	private String dermatologistName;
	
	private double price;
	
    private String startDateTime;
    
    private String endDateTime;
    
    private double dermatologistRating;
    
    private String status;
    
    

	public ExaminationPriceDermatologistDTO() {
		super();
	}

	public ExaminationPriceDermatologistDTO(Long examinationId, String examinationName, String dermatologistName, double price,
			String startDateTime, String endDateTime, double dermatologistRating, String status) {
		super();
		this.examinationName = examinationName;
		this.dermatologistName = dermatologistName;
		this.price = price;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.dermatologistRating = dermatologistRating;
		this.examinationId = examinationId;
		this.status = status;
	}

	public ExaminationPriceDermatologistDTO(Examination examination) {
		super();
		this.examinationId = examination.getId();
		this.examinationName = examination.getExamType().getName();
		this.dermatologistName = examination.getDermatologist().getFirstName() + " " +  examination.getDermatologist().getLastName();
		this.price = examination.getExamType().getPrice();
		this.startDateTime = examination.getInterval().getStartDateTime().toString();
		this.endDateTime = examination.getInterval().getEndDateTime().toString();
		this.dermatologistRating = examination.getDermatologist().getRating();
		this.status = examination.getStatus().toString();
		
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


	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	
    
}	
