package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.DrugPriceAndPharmacyDTO;
import com.example.ISA2020.dto.DrugPriceDTO;
import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.ListDrugPriceDTO;
import com.example.ISA2020.entity.DrugPrice;
import com.example.ISA2020.entity.Pharmacy;

public interface DrugPriceService {
	
	DrugPrice findById(Long id);
	
	List<DrugPrice> getAllDrugPrices();
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceAndPharmacyDTO();
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceByDrugName(String drugName);
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceByDrugCode(String drugCode);
	
	List<DrugPriceAndPharmacyDTO> getAllDrugPriceByPharmacyName(String pharmacyName);
	
	List<DrugPricePharmacyNameAddressRatingDTO> getAllDrugPriceByPharmacyCityForPatient(String pharmacyAddress);
	
	List<DrugPricePharmacyNameAddressRatingDTO> getAllDrugPriceByPharmacyNameForPatient(String pharmacyName);

	List<DrugPriceDTO> getAddDrugPricesForPharmacy(Long id);

	DrugPriceDTO createPricelist(Long pharmId, ListDrugPriceDTO pricelist);

	DrugPriceDTO changeDrugPriceInPharmacy(@Valid DrugPriceDTO drugDTO, Pharmacy pharmacy);
	
}
