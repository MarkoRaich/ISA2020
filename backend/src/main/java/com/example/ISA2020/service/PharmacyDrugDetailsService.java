package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.compositeKeys.KeyDrugPharmacyQuantity;


public interface PharmacyDrugDetailsService {
	
	DrugQuantity findById(KeyDrugPharmacyQuantity id);
	
	List<DrugQuantity> getAllPharmacyDrugDetails();
	
	void save(DrugQuantity pharmacyDrugDetails);
}
