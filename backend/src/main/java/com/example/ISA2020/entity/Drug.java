package com.example.ISA2020.entity;

import java.util.Set;

import javax.persistence.*;
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
	@OneToMany(mappedBy = "drug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DrugQuantity> drugQuantities;

    @JsonIgnore
 	@ManyToMany(mappedBy = "drug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 	private Set<DrugPrice> drugPrices;
	 

    
   
    
    
	public Drug() {
		super();
	}

	public Drug(String name,  String code) {
		super();
		this.name = name;
		this.code = code;

		this.drugQuantities = null;

		this.drugPrices = null;
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



	public Set<DrugQuantity> getDrugQuantity() {
		return drugQuantities;
	}

	public void setDrugQuantity(Set<DrugQuantity> drugQuantities) {
		this.drugQuantities = drugQuantities;
	}

	public Set<DrugPrice> getPricelistDrug() { return drugPrices; }

	public void setDrugPrices(Set<DrugPrice> drugPrices) {
		this.drugPrices = drugPrices;
	}
	
	
	
  
}
