package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.DrugQuantityInPharmacy;
import com.example.ISA2020.entity.PharmacyDrugKey;

public interface PharmacyDrugDetailsService {
	
	DrugQuantityInPharmacy findById(PharmacyDrugKey id);
	
	List<DrugQuantityInPharmacy> getAllPharmacyDrugDetails();
	
	void save(DrugQuantityInPharmacy pharmacyDrugDetails);
}
