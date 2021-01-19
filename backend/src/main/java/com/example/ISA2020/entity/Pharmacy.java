package com.example.ISA2020.entity;

import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Pharmacist;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="pharmacy")
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
	
	@Column(nullable = false, columnDefinition = "VARCHAR")
	private String description;
	
	@Column
	private double rating;   //prosecna ocena apoteke
	
	
	
	@JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacists = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)		// Dermatolozi mogu da rade u vise apoteka!!
    @JoinTable(
    			name="pharmacy_dermatologist",
    			joinColumns = @JoinColumn(name ="pharmacy_id", referencedColumnName = "id"),
    			inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
    		  )
    private Set<Dermatologist> dermatologists = new HashSet<>();
    
	
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consultation> consultations = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy")  //jedna apoteka ime vise lekova na stanju koje je jedinstveno za tu apoteku
	private Set<DrugQuantityInPharmacy> details;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy") 			 //jedna apoteka ima vise cenovnika za razlicite vremenske periode
    private Set<Pricelist> pricelistsDrug;   // a svaki od njih pripada iskljucivo ovoj apoteci
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy")
	private Set<PharmacyExaminationPrice> examinationPrices;
	
	/* MISLIM DA NE TREBA OVA ASOCIJACIJA
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable( name = "pharmacy_drug", 
				joinColumns = @JoinColumn(name ="pharmacy_id", referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id"))
	private Set<Drug> drugs;
	**/

	public Pharmacy() {
		super();
	}

	public Pharmacy(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		
		this.details = null;
		
		
	}
	
	public Pharmacy(String name) {
		super();
		this.name = name;
		this.address = null;
		
		this.details = null;
		
		
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



	public Set<DrugQuantityInPharmacy> getDetails() {
		return details;
	}

	public void setDetails(Set<DrugQuantityInPharmacy> details) {
		this.details = details;
	}



}
