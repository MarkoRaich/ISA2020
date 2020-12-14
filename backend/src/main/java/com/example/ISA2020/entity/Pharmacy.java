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

@Entity
public class Pharmacy {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Name cannot be null.")
	@Column(nullable = false)
	private String name;

	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable( name = "pharmacy_drug", joinColumns = @JoinColumn(name =
				"pharmacy_id", referencedColumnName = "id"), inverseJoinColumns
				= @JoinColumn(name = "drug_id", referencedColumnName = "id"))
	private Set<Drug> drugs;


	@OneToMany(mappedBy = "pharmacy")
	private Set<PharmacyDrugDetails> details;
	
	/* private String apiKey; */
	
	

	public Pharmacy() {
		super();
	}

	public Pharmacy(String name) {
		super();
		this.name = name;
		/* this.apiKey = apiKey; */
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

	/*
	 * public String getApiKey() { return apiKey; }
	 * 
	 * public void setApiKey(String apiKey) { this.apiKey = apiKey; }
	 */
	

}
