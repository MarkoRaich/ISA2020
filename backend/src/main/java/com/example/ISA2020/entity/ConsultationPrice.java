package com.example.ISA2020.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.ISA2020.entity.compositeKeys.KeyPharmacyConsultationPrice;

@Table(name="consultationPrice")
@Entity
public class ConsultationPrice {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private KeyPharmacyConsultationPrice id;
	
    @Column 
    private double price;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private DateTimeInterval interval; 

    @ManyToOne
    @MapsId("consultationId")
    @JoinColumn(name = "consultation_id", referencedColumnName = "id")
    private Consultation consultation;
	
	@ManyToOne
	@MapsId("pharmacyId")
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Pharmacy pharmacy;

	
	public ConsultationPrice() {
		super();
	}

	public ConsultationPrice(double price, DateTimeInterval interval, Consultation consultation, Pharmacy pharmacy) {
		super();
		this.price = price;
		this.interval = interval;
		this.consultation = consultation;
		this.pharmacy = pharmacy;
	}

	public KeyPharmacyConsultationPrice getId() {
		return id;
	}

	public void setId(KeyPharmacyConsultationPrice id) {
		this.id = id;
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



	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	
}
