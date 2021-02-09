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
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@Column
	private Double rating = 0.0;   //prosecna ocena apoteke
	
	//ocene pacijenata
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();
	
	
	
	@JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacists = new HashSet<>();  //farmaceut radi samo u jednoj apoteci
	
//	@JsonIgnore									NE MOZE OVAKO DIREKTNO JER IMAJU RAZLICITO RADNO VREME U APOTEKAMA!
//	@ManyToMany(fetch = FetchType.LAZY)		
//    @JoinTable(
//    			name="pharmacy_dermatologist",
//    			joinColumns = @JoinColumn(name ="pharmacy_id", referencedColumnName = "id"),
//    			inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
//    		  )
//    private Set<Dermatologist> dermatologists = new HashSet<>();
	
	@JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DermWorkHours> dermsWithWorkHours = new HashSet<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy="pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PharmacyAdmin> admins = new HashSet<>();
	

    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();
    
	@JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();
	
	@JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<>();
	
	@JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExaminationType> examTypes = new HashSet<>();
    


	public Pharmacy() {
		super();
		this.rating = 0.0;
	}


	
	public Pharmacy(@NotNull(message = "Name cannot be null.") String name,
			@NotNull(message = "Address cannot be null.") String address, String description, double rating) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.rating = 0.0;
	}



	public Pharmacy(String name, String address) {
		this.name = name;
		this.address = address;
		this.rating = 0.0;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
	}


	public Set<Pharmacist> getPharmacists() {
		return pharmacists;
	}


	public void setPharmacists(Set<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	

	public Set<DermWorkHours> getDermsWithWorkHours() {
		return dermsWithWorkHours;
	}



	public void setDermsWithWorkHours(Set<DermWorkHours> dermsWithWorkHours) {
		this.dermsWithWorkHours = dermsWithWorkHours;
	}



	public Set<PharmacyAdmin> getAdmins() {
		return admins;
	}


	public void setAdmins(Set<PharmacyAdmin> admins) {
		this.admins = admins;
	}



	public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}



	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}



	public Set<Reservation> getReservations() {
		return reservations;
	}



	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}



	public Set<Grade> getGrades() {
		return grades;
	}



	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}



	public Set<Examination> getExaminations() {
		return examinations;
	}



	public void setExaminations(Set<Examination> examinations) {
		this.examinations = examinations;
	}

	

}
