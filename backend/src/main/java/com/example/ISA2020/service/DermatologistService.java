package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.DermatologistDTO;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.PharmacyAdmin;

public interface DermatologistService {
	
	Dermatologist findById(Long id);
	
	Dermatologist findByUsername(String username);
	
	List<Dermatologist> getAllDermatologists();

	List<DermatologistDTO> findAllDermatologistsInPharmacy(Pharmacy pharmacy);
	
	List<DermatologistDTO> getAllAvailableDermatologists(Pharmacy pharmacy, String startDateTime, String endDateTime);
	
	Dermatologist getDermatologist(Long id);

	DermatologistDTO deleteDermatologistFromPharmacy(Long pharmacyId, Long id);

	List<DermatologistDTO> getAllActiveDermatologists();

	List<DermatologistDTO> searchDermatologistsInPharmacy(Long id, String firstName, String lastName);

	List<DermatologistDTO> findAllDermatologistsNotInPharmacy(Pharmacy pharmacy);

	DermatologistDTO addDermatologistToPharmacy(@Valid DermatologistDTO dermatologistDTO, PharmacyAdmin pharmacyAdmin);


}
