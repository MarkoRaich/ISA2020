package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.VacationRequestStatus;


@Entity
public class VacationRequestPharm { //odobrava administrator sistema

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

	@Enumerated
	private VacationRequestStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacist_id", referencedColumnName = "id")
	private Pharmacist pharmacist;

	
	
	//KONSTRUKTORI
	public VacationRequestPharm() {
		super();
	}

	public VacationRequestPharm(DateTimeInterval interval, VacationRequestStatus status, Pharmacist pharmacist) {
		super();
		this.interval = interval;
		this.status = status;
		this.pharmacist = pharmacist;
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

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
	
	
	
	
}
