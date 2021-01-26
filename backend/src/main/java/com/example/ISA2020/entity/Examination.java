package com.example.ISA2020.entity;

import java.util.Set;

import javax.persistence.*;

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

	@Enumerated
	private ExaminationStatus status;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

	//Vise pregleda mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
	

	
	@OneToOne(cascade = CascadeType.ALL)
	private Complaint complaint;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "examinationReport_id", referencedColumnName = "id")
	private ExaminationReport examinationReport;

}
