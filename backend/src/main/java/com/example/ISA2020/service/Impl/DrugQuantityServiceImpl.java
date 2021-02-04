package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.PharmDrugQuantityKey;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.enumeration.EntityStatus;
import com.example.ISA2020.repository.DrugQuantityRepository;
import com.example.ISA2020.service.DrugQuantityService;

@Service
public class DrugQuantityServiceImpl implements DrugQuantityService {
	
	@Autowired
	private DrugQuantityRepository drugQuantityRepo;
	
	
	
	@Override
	public DrugQuantity findById(PharmDrugQuantityKey id) {
		
		return drugQuantityRepo.findOneById(id);
	}

	@Override
	public List<DrugQuantity> getAllDrugQuantities() {
		
		return drugQuantityRepo.findAll();
	}

	@Override
	public List<DrugSearchDTO> findAllDrugsInPharmacy(Long pharmId) {
		
		List<DrugQuantity> drugQuantities =  drugQuantityRepo.findByPharmacyIdAndStatusNot(pharmId, EntityStatus.DELETED);
		
		List<DrugSearchDTO> drugs = new ArrayList<>();
		
		for(DrugQuantity drugQ : drugQuantities) {
			drugs.add( new DrugSearchDTO( drugQ.getDrug().getId(),
					  					  drugQ.getDrug().getName(),
										  drugQ.getDrug().getCode(),
										  drugQ.getQuantity(),
										  new PharmacyDTO(drugQ.getPharmacy()) 
										 )
					 );
		}
		return drugs;
		
	}
	
	@Override
	public DrugDTO deleteDrugFromPharmacy(Long pharmId, Long drugId) {
		
		DrugQuantity druqQuantity = drugQuantityRepo.findOneById(new PharmDrugQuantityKey(pharmId, drugId));
		return null;
	}
	
	
	private List<DrugDTO> convertToDTO(List<Drug> drugs){
		
		List<DrugDTO> drugDTOs = new ArrayList<>();
		for( Drug drug : drugs) {
			drugDTOs.add(new DrugDTO(drug));
		}
		return drugDTOs;
	}

	
	
	
}
