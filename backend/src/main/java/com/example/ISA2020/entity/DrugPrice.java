package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.dto.DrugPriceDTO;
import com.example.ISA2020.entity.compositeKeys.*;

@Table(name="drugPrice")
@Entity
public class DrugPrice {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private double price;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private DateTimeInterval interval;


	@ManyToOne
	@JoinColumn(name = "drug_id", referencedColumnName = "id")
	private Drug drug;

	@ManyToOne
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;


	//KONSTRUKTORI
	public DrugPrice() {}

	

	public DrugPrice(double price, DateTimeInterval interval, Drug drug, Pharmacy pharmacy) {
		super();
		this.price = price;
		this.interval = interval;
		this.drug = drug;
		this.pharmacy = pharmacy;
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

	public DateTimeInterval getInterval() {
		return interval;
	}

	public void setInterval(DateTimeInterval interval) {
		this.interval = interval;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
}
