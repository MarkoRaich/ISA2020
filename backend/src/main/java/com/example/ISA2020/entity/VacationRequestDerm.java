package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.VacationRequestStatus;


@Entity
public class VacationRequestDerm { //odobrava admin apoteke
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

	@Enumerated(EnumType.STRING)
	private VacationRequestStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pharmacy pharmacy;

	
	//KONSTRUKTORI
	public VacationRequestDerm() {
		super();
	}
	
	public VacationRequestDerm(DateTimeInterval interval, VacationRequestStatus status, Dermatologist dermatologist, Pharmacy pharmacy) {
		super();
		this.interval = interval;
		this.status = status;
		this.dermatologist = dermatologist;
		this.pharmacy=pharmacy;
	}
	
	//GETERI I SETERI
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DateTimeInterval getInterval() {
		return interval;
	}

	public void setInterval(DateTimeInterval interval) {
		this.interval = interval;
	}

	public VacationRequestStatus getStatus() {
		return status;
	}

	public void setStatus(VacationRequestStatus status) {
		this.status = status;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	
}
