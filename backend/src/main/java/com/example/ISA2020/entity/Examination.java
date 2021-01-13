package com.example.ISA2020.entity;

import javax.persistence.*;

//PREGLED KOD DERMATOLOGA
//SAVETOVANJE KOD FARMACEUTA ?

@Entity
public class Examination {    

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	//TIP STATUS VREMENSKI INTERVAL....


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
}
