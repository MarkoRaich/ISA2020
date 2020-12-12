package com.example.ISA2020.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PharmacyDrugKey implements Serializable {

    @Column(name = "pharmacy_id")
    Long pharmacyId;

    @Column(name = "drug_id")
    Long drugId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}
