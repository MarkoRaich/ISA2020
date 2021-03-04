package com.example.ISA2020.dto;

import java.time.LocalDateTime;

import com.example.ISA2020.entity.Examination;

public class ExaminationPriceDTO {
	
	private Long examinationId;

	private String examinationName;
	
	private String pharmacyName;
	
	private double price;
	
    private String startDateTime;
    
    private String endDateTime;
    
    private String status;
    
    

	public ExaminationPriceDTO() {
		super();
	}



	public ExaminationPriceDTO(Long examinationId, String examinationName, String pharmacyName, double price, String startDateTime,
			String endDateTime, String status) {
		super();
		this.examinationId = examinationId;
		this.examinationName = examinationName;
		this.pharmacyName = pharmacyName;
		this.price = price;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.status = status;
	}



	public ExaminationPriceDTO(Examination examination) {
		super();
		this.examinationId = examination.getId();
		this.examinationName = examination.getExamType().getName();
		this.pharmacyName = examination.getPharmacy().getName();
		this.price = examination.getExamType().getPrice();
		this.startDateTime = examination.getInterval().getStartDateTime().toString();
		this.endDateTime = examination.getInterval().getEndDateTime().toString();
		this.status = examination.getStatus().toString();
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
