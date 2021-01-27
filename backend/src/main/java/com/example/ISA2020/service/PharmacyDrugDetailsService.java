package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.PharmDrugQuantityKey;
import com.example.ISA2020.entity.PharmDrugQuantityKey;

public interface PharmacyDrugDetailsService {
	
	DrugQuantity findById(PharmDrugQuantityKey id);
	
	List<DrugQuantity> getAllPharmacyDrugDetails();
	
	void save(DrugQuantity pharmacyDrugDetails);
}
