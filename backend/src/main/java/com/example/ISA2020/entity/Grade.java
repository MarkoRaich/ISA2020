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
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;

@Table(name="grade")
@Entity
public class Grade {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotNull(message = "Grade cannot be null.")
    @Column(nullable = false)
    private double grade;
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", referencedColumnName = "id")
	private Drug drug;
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacist_id", referencedColumnName = "id")
	private Pharmacist pharmacist;
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;

    
	public Grade() {
		super();
	}


	public double getGrade() {
		return grade;
	}


	public void setGrade(double grade) {
		this.grade = grade;
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


	public Pharmacist getPharmacist() {
		return pharmacist;
	}


	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}


	public Dermatologist getDermatologist() {
		return dermatologist;
	}


	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
