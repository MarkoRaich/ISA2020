package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.PharmacistDTO;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Pharmacist;

public interface PharmacistService {

	Pharmacist findById(Long id);
	
	Pharmacist getPharmacist(Long id);
	
	Pharmacist findByUsername(String username);
	
	List<Pharmacist> getAllPharmacists();

	List<PharmacistDTO> findAllPharmacistsInPharmacy(Long id);

	List<PharmacistDTO> searchPharmacistsInPharmacy(Long id, String firstName, String lastName);

	PharmacistDTO deletePharmacist(Long pharmacyId, Long id2);

	PharmacistDTO create(@Valid PharmacistDTO pharmacistDTO, Pharmacy pharmacy);
}
