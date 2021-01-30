package com.example.ISA2020.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Pharmacy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Name cannot be null.")
	@Column(nullable = false)
	private String name;
	
	@NotNull(message = "Address cannot be null.")
	@Column(nullable = false)
	private String address;
	
	@NotNull(message = "City cannot be null.")
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false, columnDefinition = "TEXT")
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
	@OneToMany(mappedBy="pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacyAdmin> admins = new HashSet<>();
	
	
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consultation> consultations = new HashSet<>();
    


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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(Set<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	public Set<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public void setDermatologists(Set<Dermatologist> dermatologists) {
		this.dermatologists = dermatologists;
	}

	public Set<PharmacyAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<PharmacyAdmin> admins) {
		this.admins = admins;
	}

	public Set<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(Set<Examination> examinations) {
		this.examinations = examinations;
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

}
