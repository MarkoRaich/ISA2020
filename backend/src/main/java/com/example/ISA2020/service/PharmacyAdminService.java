package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.EditPharmAdminDTO;
import com.example.ISA2020.dto.PharmacyAdminDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;

public interface PharmacyAdminService {

	List<PharmacyAdminDTO> getAllPharmacyAdminsForPharmacy(Long id);

	EditPharmAdminDTO getPharmacyAdmin(Long id);
	
	PharmacyAdminDTO create(PharmacyAdminDTO pharmacyAdminDTO);

	PharmacyAdminDTO editPersonalInformation(@Valid EditPharmAdminDTO pharmacyAdminDTO);

	PharmacyAdmin getLoginAdmin();

}
