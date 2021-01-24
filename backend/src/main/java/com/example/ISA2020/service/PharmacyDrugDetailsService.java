package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.PharmacyDrugKey;

public interface PharmacyDrugDetailsService {
	
	DrugQuantity findById(PharmacyDrugKey id);
	
	List<DrugQuantity> getAllPharmacyDrugDetails();
	
	void save(DrugQuantity pharmacyDrugDetails);
}
