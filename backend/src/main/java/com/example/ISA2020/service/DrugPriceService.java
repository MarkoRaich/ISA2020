package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.DrugPriceAndPharmacyDTO;
import com.example.ISA2020.entity.DrugPrice;

public interface DrugPriceService {
	
	DrugPrice findById(Long id);
	
	List<DrugPrice> getAllDrugPrices();
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceAndPharmacyDTO();
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceByDrugName(String drugName);
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceByDrugCode(String drugCode);
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceByPharmacyName(String pharmacyName);
}
