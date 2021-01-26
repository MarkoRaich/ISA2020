package com.example.ISA2020.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Table(name="drugQuantity")
@Entity
public class DrugQuantity {

    @EmbeddedId
    private PharmacyDrugKey id;

    @Column
    @Min(0)
    private int quantity;

	@ManyToOne
	@MapsId("drugId")
	@JoinColumn(name = "drug_id", referencedColumnName = "id")
	private Drug drug;

    @ManyToOne
    @MapsId("pharmacyId")
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Pharmacy pharmacy;
    


    
	public DrugQuantity() {
		super();
	}


	public DrugQuantity(Pharmacy pharmacy, Drug drug, int quantity) {
		super();
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

}
