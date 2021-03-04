package com.example.ISA2020.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

import com.example.ISA2020.entity.compositeKeys.KeyDrugPharmacyQuantity;
import com.example.ISA2020.enumeration.EntityStatus;


@Entity
public class DrugQuantity {

    @EmbeddedId
    private KeyDrugPharmacyQuantity id;

    @Column
    @Min(0)
    private int quantity;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@MapsId("drugId")
	@JoinColumn(name = "drug_id", referencedColumnName = "id")
	private Drug drug;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId("pharmacyId")
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Pharmacy pharmacy;
    
	
	@Enumerated(EnumType.STRING)
	@Column()
	private EntityStatus status;

    
	public DrugQuantity() {
		super();
	}


	public DrugQuantity(KeyDrugPharmacyQuantity id, Pharmacy pharmacy, Drug drug, int quantity, EntityStatus status) {
		super();
		this.pharmacy = pharmacy;
		this.drug = drug;
		this.quantity = quantity;
		this.status = status;
		this.id=id;
	}

	//GETERI I SETERI
	public KeyDrugPharmacyQuantity getId() {
		return id;
	}


	public void setId(KeyDrugPharmacyQuantity id) {
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


	public EntityStatus getStatus() {
		return status;
	}


	public void setStatus(EntityStatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "DrugQuantity [id=" + id + ", quantity=" + quantity + ", drug=" + drug + ", pharmacy=" + pharmacy
				+ ", status=" + status + "]";
	}

}
