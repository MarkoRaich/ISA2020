package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.PharmDrugQuantityKey;
import com.example.ISA2020.entity.Pharmacy;

public interface DrugQuantityService {
	
	//DrugQuantity findById(Long id);
	
	List<DrugQuantity> getAllDrugQuantities();

	List<DrugSearchDTO> findAllDrugsInPharmacy(Long id);

	DrugSearchDTO deleteDrugFromPharmacy(Long pharmId, Long drugId);

	DrugQuantity findById(PharmDrugQuantityKey id);

	List<DrugSearchDTO> searchDrugsInPharmacy(Long id, String name);

	List<DrugSearchDTO> findAllDrugsNotInPharmacy(Long id);

	DrugSearchDTO addDrugInPharmacy(@Valid DrugSearchDTO drugDTO, Pharmacy pharmacy);

	DrugSearchDTO changeDrugQuantityInPharmacy(@Valid DrugSearchDTO drugDTO, Pharmacy pharmacy);
}
