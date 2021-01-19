package com.example.ISA2020.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.enumeration.DrugType;
import com.fasterxml.jackson.annotation.JsonIgnore;


// PREPARAT - LEK
@Table(name="drug")
@Entity
public class Drug {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null.")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Code cannot be null.")
    @Column(nullable = false)
    private String code;
    
    private DrugType drugType;
    
    //DRUG SPECS 3.42 ima jos atributa mozda u posebnu klasu!?
    
    @JsonIgnore
 	@ManyToMany(mappedBy = "drugs") 
 	private Set<Pharmacy> pharmacies;
    
    @JsonIgnore
 	@ManyToMany(mappedBy = "drugs") 
 	private Set<Pricelist> pricelistDrug;
	 
    @JsonIgnore
    @OneToMany(mappedBy = "drug")
    private Set<DrugQuantityInPharmacy> details;
    
   
    
    
	public Drug() {
		super();
	}

	public Drug(String name,  String code) {
		super();
		this.name = name;
		this.code = code;
		this.pharmacies = null;
		this.details = null;
		this.pharmacies = null;
		this.pricelistDrug = null;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(Set<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

	public Set<DrugQuantityInPharmacy> getDetails() {
		return details;
	}

	public void setDetails(Set<DrugQuantityInPharmacy> details) {
		this.details = details;
	}

	public Set<Pricelist> getPricelistDrug() {
		return pricelistDrug;
	}

	public void setPricelist(Set<Pricelist> pricelistDrug) {
		this.pricelistDrug = pricelistDrug;
	}
	
	
	
  
}
