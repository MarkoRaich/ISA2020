package com.example.ISA2020.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

//Rezervacija leka
@Table(name="reservation")
@Entity
public class Reservation {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Enumerated
	private ReservationStatus status;
	
	//Vise rezervacija leka mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Drug drug;
	
	//interval sadrzi pocetak i kraj intervala
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;
	
	@Column 
	private String generatedKey;
	
	//Dodati konstruktore, getere i setere
	
	
	
	//metoda koja generise random string za preuzimanje leka koji je rezervisan (salje se potvrdom na mail)
	public String randomStringGenerator() {
		 
	    int length = 5;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(generatedString);
	    
	    return generatedString;
	}

}
