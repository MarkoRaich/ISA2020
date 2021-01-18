package com.example.ISA2020.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.ISA2020.entity.users.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;

//PREGLED KOD DERMATOLOGA
//SAVETOVANJE KOD FARMACEUTA ?

@Table(name = "examination")
@Entity
public class Examination {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// TIP STATUS VREMENSKI INTERVAL....
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pharmacy pharmacy;

	@OneToOne(cascade = CascadeType.ALL)
	private Complaint complaint;

	@OneToOne(cascade = CascadeType.ALL)
	private ExaminationReport examinationReport;

	//Vise pregleda mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private Patient patient; 
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Prescription prescription;
	
	@JsonIgnore
    @OneToMany(mappedBy = "examination")
    private Set<PharmacyExaminationPrice> examinationPrices;
}
