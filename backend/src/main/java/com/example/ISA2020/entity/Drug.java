package com.example.ISA2020.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.enumeration.DrugForm;
import com.example.ISA2020.enumeration.DrugType;
import com.example.ISA2020.enumeration.IssuanceType;


// PREPARAT - LEK (3.42)
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
    
    //Sifra leka
    @NotNull(message = "Code cannot be null.")
    @Column(nullable = false)
    private String code;
    
    //Proizvodjac
    @NotNull(message = "Manufacturer cannot be null.")
    @Column(nullable = false)
    private String manufacturer;
    
    //Sastav leka??
    @NotNull(message = "Composition cannot be null.")
    @Column(nullable = false)
    private String composition;
    
    //Dodatne napomene
    @NotNull(message = "Notes cannot be null.")
    @Column(nullable = false)
    private String notes;
    
    @Enumerated(EnumType.STRING)
    @Column()
    private DrugForm form;
  
    //Tip leka (vrsta)
    @Enumerated(EnumType.STRING)
    @Column()
    private DrugType type;
    
    @Enumerated(EnumType.STRING)
    @Column()
    private IssuanceType prescription;
    
    @OneToMany(mappedBy = "drug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();
    
    //DRUG SPECS 3.42 ima jos atributa mozda u posebnu klasu!?  
   
    
    //KONSTRUKTORI
	public Drug() {
		super();
	}

	public Drug(String name,  String code) {
		super();
		this.name = name;
		this.code = code;
	

	}

	//GETERI I SETERI
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public DrugForm getForm() {
		return form;
	}

	public void setForm(DrugForm form) {
		this.form = form;
	}

	public DrugType getType() {
		return type;
	}

	public void setType(DrugType type) {
		this.type = type;
	}

	public IssuanceType getPrescription() {
		return prescription;
	}

	public void setPrescription(IssuanceType prescription) {
		this.prescription = prescription;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
