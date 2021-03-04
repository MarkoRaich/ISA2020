package com.example.ISA2020.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.sun.istack.NotNull;

//Rezervacija leka
@Table(name="reservation")
@Entity
public class Reservation {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Column 
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	//Vise rezervacija leka mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Drug drug;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pharmacy pharmacy;
	
	//interval sadrzi pocetak i kraj intervala
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;
	
	@Column 
	private String generatedKey;
	
	@Column
	private double sellingPrice;		//pri kompletiranju rezervacije ovde se upisuje prodajna cena
	
	
	//KONSTRUKTORI
	public Reservation() {
		super();
		this.generatedKey = randomStringGenerator();
	}

	public Reservation(ReservationStatus status, Patient patient, Drug drug, DateTimeInterval interval,
			String generatedKey, Integer quantity, double sellingPrice) {
		super();
		this.status = status;
		this.patient = patient;
		this.drug = drug;
		this.interval = interval;
		this.generatedKey = randomStringGenerator();
		this.quantity = quantity;
		this.sellingPrice=sellingPrice;
	}

	//metoda koja generise random string za preuzimanje leka koji je rezervisan (salje se potvrdom na mail)
	public String randomStringGenerator() {
		 
	    int length = 5;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    //System.out.println(generatedString);
	    
	    return generatedString;
	}

	
	
	//GETERI I SETERI
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public DateTimeInterval getInterval() {
		return interval;
	}

	public void setInterval(DateTimeInterval interval) {
		this.interval = interval;
	}

	public String getGeneratedKey() {
		return generatedKey;
	}

	public void setGeneratedKey(String generatedKey) {
		this.generatedKey = generatedKey;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	

	
}
