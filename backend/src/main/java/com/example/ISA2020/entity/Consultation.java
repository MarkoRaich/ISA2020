package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.ConsultationStatus;


@Entity
public class Consultation {		//SAVETOVANJE KOD FARMACEUTA
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated
	private ConsultationStatus status;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

	//Vise savetovanja mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacist_id", referencedColumnName = "id")
	private Pharmacist pharmacist;

	
	
	//@OneToOne(cascade = CascadeType.ALL)
	//private Complaint complaint;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "consultationReport_id", referencedColumnName = "id")
	private ConsultationReport consultationReport;
	
	
	public Consultation() {}


	public Consultation(ConsultationStatus status, DateTimeInterval interval, Patient patient, Pharmacy pharmacy,
			Pharmacist pharmacist) {
		super();
		this.status = status;
		this.interval = interval;
		this.patient = patient;
		this.pharmacy = pharmacy;
		this.pharmacist = pharmacist;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ConsultationStatus getStatus() {
		return status;
	}


	public void setStatus(ConsultationStatus status) {
		this.status = status;
	}


	public DateTimeInterval getInterval() {
		return interval;
	}


	public void setInterval(DateTimeInterval interval) {
		this.interval = interval;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
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
	
	
	
	
}
