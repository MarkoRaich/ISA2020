package com.example.ISA2020.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.Pharmacy;

public class PharmacyDrugDetailsDTO {
	
	/* private PharmacyDrugKey id; */

    private Pharmacy pharmacy;

    private Drug drug;

    private int quantity;
    
    
}
