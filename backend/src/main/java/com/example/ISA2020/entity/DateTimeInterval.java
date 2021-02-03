package com.example.ISA2020.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class DateTimeInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime endDateTime;
    
    @OneToMany(mappedBy = "interval", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();
    
    @OneToMany(mappedBy = "interval", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PharmacyExaminationPrice> examinationPrices = new HashSet<>();
    
//    @OneToMany(mappedBy = "interval", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<DrugPrice> drugPrices = new HashSet<>();
    
    //KONSTRUKTORI
	public DateTimeInterval() {
		super();
	}
	
	


	public DateTimeInterval(LocalDateTime startDateTime, LocalDateTime endDateTime, Set<Reservation> reservations,
			Set<PharmacyExaminationPrice> examinationPrices) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.reservations = reservations;
		this.examinationPrices = examinationPrices;
		//this.drugPrices = drugPrices;
	}




	//GETERI I SETERI
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}


	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}


	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}


	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}




	public Set<Reservation> getReservations() {
		return reservations;
	}




	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}




	public Set<PharmacyExaminationPrice> getExaminationPrices() {
		return examinationPrices;
	}




	public void setExaminationPrices(Set<PharmacyExaminationPrice> examinationPrices) {
		this.examinationPrices = examinationPrices;
	}



}   

    
