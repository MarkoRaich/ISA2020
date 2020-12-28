package com.example.ISA2020.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pharmacy {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Name cannot be null.")
	@Column(nullable = false)
	private String name;
	
	@NotNull(message = "Address cannot be null.")
	@Column(nullable = false)
	private String address;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable( name = "pharmacy_drug", joinColumns = @JoinColumn(name =
				"pharmacy_id", referencedColumnName = "id"), inverseJoinColumns
				= @JoinColumn(name = "drug_id", referencedColumnName = "id"))
	private Set<Drug> drugs;

	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy")
	private Set<PharmacyDrugDetails> details;
	
	
	

	public Pharmacy() {
		super();
	}

	public Pharmacy(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.drugs = null;
		this.details = null;
//		this.drugs = null;
	}
	
	public Pharmacy(String name) {
		super();
		this.name = name;
		this.address = null;
		this.drugs = null;
		this.details = null;
//		this.drugs = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(Set<Drug> drugs) {
		this.drugs = drugs;
	}

	public Set<PharmacyDrugDetails> getDetails() {
		return details;
	}

	public void setDetails(Set<PharmacyDrugDetails> details) {
		this.details = details;
	}



}
