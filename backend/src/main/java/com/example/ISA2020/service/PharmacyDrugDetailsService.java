package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.PharmacyDrugDetails;
import com.example.ISA2020.entity.PharmacyDrugKey;

public interface PharmacyDrugDetailsService {
	
	PharmacyDrugDetails findById(PharmacyDrugKey id);
	
	List<PharmacyDrugDetails> getAllPharmacyDrugDetails();
	
	void save(PharmacyDrugDetails pharmacyDrugDetails);
}
