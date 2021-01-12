package com.example.ISA2020.entity;

public class TenderOffer {
	
    private String code;
    private String name;
    private int quantity;
    private double price;
    //private String pharmacyName;
    
    
	public TenderOffer() {
		super();
	}

	public TenderOffer(String code, String name, int quantity, double price/*, String pharmacyName*/) {
		super();
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		//this.pharmacyName = pharmacyName;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	/*
	 * public String getPharmacyName() { return pharmacyName; }
	 * 
	 * public void setPharmacyName(String pharmacyName) { this.pharmacyName =
	 * pharmacyName; }
	 */
	
    
    
}
