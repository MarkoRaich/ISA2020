package com.example.ISA2020.dto;

import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.DrugPrice;

public class DrugPriceDTO {

	private Long id;
	
	private double price;
	
	private String startDate;
	
	private String endDate;
	
	private DrugDTO drug;
	
	private PharmacyDTO pharmacy;

	
	
	
	public DrugPriceDTO() {
		super();
	}


	public DrugPriceDTO(Long id, double price, String startDate, String endDate, DrugDTO drug, PharmacyDTO pharmacy) {
		super();
		this.id = id;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.drug = drug;
		this.pharmacy = pharmacy;
	}



	public DrugPriceDTO(DrugPrice drugPrice) {
		this.id=drugPrice.getId();
		this.price=drugPrice.getPrice();
		this.startDate=drugPrice.getInterval().getStartDateTime().toString();
		this.endDate=drugPrice.getInterval().getEndDateTime().toString();
		this.drug=new DrugDTO(drugPrice.getDrug());
		this.pharmacy=new PharmacyDTO(drugPrice.getPharmacy());
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}








	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public DrugDTO getDrug() {
		return drug;
	}




	public void setDrug(DrugDTO drug) {
		this.drug = drug;
	}




	public PharmacyDTO getPharmacy() {
		return pharmacy;
	}




	public void setPharmacy(PharmacyDTO pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	
	
}
