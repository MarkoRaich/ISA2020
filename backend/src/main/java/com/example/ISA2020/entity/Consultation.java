package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;

public class Consultation {		//SAVETOVANJE KOD FARMACEUTA
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pharmacy pharmacy;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pharmacist pharmacist;
	
	//Vise savetovanja mogu biti povezani sa jednim pacijentom
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private Patient patient;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Complaint complaint;
	
}
