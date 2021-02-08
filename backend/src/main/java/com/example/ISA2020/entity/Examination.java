package com.example.ISA2020.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "examination")
@Entity
public class Examination {		//PREGLED KOD DERMATOLOGA

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
	private ExaminationStatus status;

	//Pocetak i kraj pregleda
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

	//Vise pregleda mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Complaint complaint;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "examinationReport_id", referencedColumnName = "id")
	private ExaminationReport examinationReport = null;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pharmacy pharmacy;
	
	
	//KONSTRUKTORI
	public Examination() {
		super();
		this.status = ExaminationStatus.AVAILABLE;
		this.examinationReport = null;
	}

	public Examination(ExaminationStatus status, DateTimeInterval interval, Patient patient,
			Dermatologist dermatologist, Complaint complaint, ExaminationReport examinationReport) {
		super();
		this.status = status;
		this.interval = interval;
		this.patient = patient;
		this.dermatologist = dermatologist;
		this.complaint = complaint;
		this.examinationReport = examinationReport;
		this.status = ExaminationStatus.AVAILABLE;
	}
	
	//GETERI I SETERI
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExaminationStatus getStatus() {
		return status;
	}

	public void setStatus(ExaminationStatus status) {
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


	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public ExaminationReport getExaminationReport() {
		return examinationReport;
	}

	public void setExaminationReport(ExaminationReport examinationReport) {
		this.examinationReport = examinationReport;
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

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	
	
}
