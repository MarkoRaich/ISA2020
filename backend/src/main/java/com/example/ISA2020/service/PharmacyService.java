package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.EditPharmacyDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.Pharmacy;

public interface PharmacyService {
	
	Pharmacy findById(Long id);
    
	Pharmacy findByName(String name);
	
	Pharmacy createPharmacy(PharmacyDTO pharmacyDTO);
	
	List<PharmacyDTO> getAllPharmacies();

	EditPharmacyDTO edit(@Valid EditPharmacyDTO pharmacyDTO, Long pharmId);
	
	List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyAddress();
	
	List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyName();
	
	List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyRating();
	
	List<PharmacyDTO> getAllPharmaciesSortedByAddressForAddress(String pharmacyAddress);
	
	List<PharmacyDTO> getAllPharmaciesSortedByNameForName(String pharmacyName);

	List<PharmacyDTO> searchPharmaciesByNameAndAddress(Long id, String name, String address);
	
}
