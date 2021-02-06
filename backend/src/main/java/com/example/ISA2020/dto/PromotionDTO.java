package com.example.ISA2020.dto;

public class PromotionDTO {
	
	private String content;
	
	private String pharmacyName;
	
	private String startDate;

	private String endDate;
	
	
	
	public PromotionDTO() {
		super();
	}



	public PromotionDTO(String content, String pharmacyName, String startDate, String endDate) {
		super();
		this.content = content;
		this.pharmacyName = pharmacyName;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}


	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
	
	
}
