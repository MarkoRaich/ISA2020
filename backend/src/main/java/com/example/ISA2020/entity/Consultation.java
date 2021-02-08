package com.example.ISA2020.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.ConsultationStatus;


@Entity
public class Consultation {		//SAVETOVANJE KOD FARMACEUTA
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotNull(message = "Name cannot be null.")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Description cannot be null.")
    @Column(nullable = false)
    private String description;
    
	
	@Enumerated
	private ConsultationStatus status;


	//Vise savetovanja mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacist_id", referencedColumnName = "id")
	private Pharmacist pharmacist;

	
	
	//@OneToOne(cascade = CascadeType.ALL)
	//private Complaint complaint;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "consultationReport_id", referencedColumnName = "id")
	private ConsultationReport consultationReport = null;

	

	//KONSTRUKTORI
	public Consultation() {
		super();
		this.status = ConsultationStatus.AVAILABLE;
		this.consultationReport = null;
	}



	public Consultation(String name, String description, Patient patient, Pharmacist pharmacist,
			ConsultationReport consultationReport) {
		super();
		this.name = name;
		this.description = description;
		this.status = ConsultationStatus.AVAILABLE;
		this.patient = patient;
		this.pharmacist = pharmacist;
		this.consultationReport = null;
	}
	

//	public Consultation(ConsultationStatus status, DateTimeInterval interval, Patient patient, Pharmacy pharmacy,
//			Pharmacist pharmacist) {
//		super();
//		this.status = status;
//		this.interval = interval;
//		this.patient = patient;
//		this.pharmacy = pharmacy;
//		this.pharmacist = pharmacist;
//	}

	

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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public ConsultationStatus getStatus() {
		return status;
	}



	public void setStatus(ConsultationStatus status) {
		this.status = status;
	}


	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Pharmacist getPharmacist() {
		return pharmacist;
	}



	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}



	public ConsultationReport getConsultationReport() {
		return consultationReport;
	}



	public void setConsultationReport(ConsultationReport consultationReport) {
		this.consultationReport = consultationReport;
	}
	
	
}
