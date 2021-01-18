package com.example.ISA2020.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PharmacyExaminationPrice {
	
	@EmbeddedId
    private PharmacyExaminationKey id;

    @ManyToOne
    @MapsId("pharmacyId")
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @MapsId("examinationId")
    @JoinColumn(name = "examination_id")
    private Examination examination;
    
    @Column 
    private double price;
}
