package com.example.ISA2020.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ISA2020.entity.users.Patient;

//PREGLED KOD DERMATOLOGA
//SAVETOVANJE KOD FARMACEUTA ?

@Table(name="examination")
@Entity
public class Examination {    

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	//TIP STATUS VREMENSKI INTERVAL....


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
    
	/*
	 * @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) private
	 * Patient patient;
	 */
}
