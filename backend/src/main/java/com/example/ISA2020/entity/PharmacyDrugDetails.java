package com.example.ISA2020.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PharmacyDrugDetails {

    @EmbeddedId
    private PharmacyDrugKey id;

    @ManyToOne
    @MapsId("pharmacyId")
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @MapsId("drugId")
    @JoinColumn(name = "drug_id")
    private Drug drug;

    private int quantity;

    
	public PharmacyDrugDetails() {
		super();
	}


	public PharmacyDrugDetails(PharmacyDrugKey id, Pharmacy pharmacy, Drug drug, int quantity) {
		super();
		this.id = id;
		this.pharmacy = pharmacy;
		this.drug = drug;
		this.quantity = quantity;
	}


	public PharmacyDrugKey getId() {
		return id;
	}


	public void setId(PharmacyDrugKey id) {
		this.id = id;
	}


	public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	public Drug getDrug() {
		return drug;
	}


	public void setDrug(Drug drug) {
		this.drug = drug;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


    
    // standard constructors, getters, and setters
}
