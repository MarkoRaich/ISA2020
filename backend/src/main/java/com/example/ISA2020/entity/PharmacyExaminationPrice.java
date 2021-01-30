package com.example.ISA2020.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PharmacyExaminationPrice {
	
	@EmbeddedId
    private PharmacyExaminationKey id;

    @ManyToOne
    @MapsId("pharmacyId")
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @MapsId("examinationId")
    @JoinColumn(name = "examination_id")
    private Examination examination;
    
    @Column 
    private double price;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

    
    //KONSTRUKTORI
	public PharmacyExaminationPrice() {
		super();
	}


	public PharmacyExaminationPrice(Pharmacy pharmacy, Examination examination, double price,
			DateTimeInterval interval) {
		super();
		this.pharmacy = pharmacy;
		this.examination = examination;
		this.price = price;
		this.interval = interval;
	}

	
	//GETERI I SETERI
	public PharmacyExaminationKey getId() {
		return id;
	}


	public void setId(PharmacyExaminationKey id) {
		this.id = id;
	}


	public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	public Examination getExamination() {
		return examination;
	}


	public void setExamination(Examination examination) {
		this.examination = examination;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public DateTimeInterval getInterval() {
		return interval;
	}


	public void setInterval(DateTimeInterval interval) {
		this.interval = interval;
	}
	
	
    
    
    
    
}
