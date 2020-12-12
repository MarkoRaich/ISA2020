package com.example.ISA2020.service;

import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Pharmacy;

public interface PharmacyService {
	
	Pharmacy findById(Long id);
    
	Pharmacy findByName(String name);
	
	Pharmacy createPharmacy(PharmacyDTO pharmacyDTO);
}
