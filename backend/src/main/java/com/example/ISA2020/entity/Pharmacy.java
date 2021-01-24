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
    private Set<Pharmacist> pharmacists = new HashSet<>();  //farmaceut radi samo u jednoj apoteci
	
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
	private Set<DrugQuantity> drugQuantity;

	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy")  //jedna apoteka ime vise cena za lekove koji su jedinstveni za tu apoteku
	private Set<DrugPrice> drugPrice;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy")
	private Set<PharmacyExaminationPrice> examinationPrices;
	


	public Pharmacy() {
		super();
	}

	public Pharmacy(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		
		
		
		
	}
	
	public Pharmacy(String name) {
		super();
		this.name = name;
		this.address = null;
		
		
		
		
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



	

}
