package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.PharmDrugQuantityKey;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.enumeration.EntityStatus;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.enumeration.UserStatus;
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
										  drugQ.getQuantity() )								
					 );
		}
		return drugs;
		
	}
	
	@Override
	public DrugSearchDTO deleteDrugFromPharmacy(Long pharmId, Long drugId) {
		
		DrugQuantity drugQuantity = drugQuantityRepo.findOneById(new PharmDrugQuantityKey(pharmId, drugId));
		if(drugQuantity == null) {
			return null;
		}
		
		Set<Reservation> reservations = drugQuantity.getDrug().getReservations();
		for(Reservation res: reservations) {
			if(res.getStatus() == ReservationStatus.ACTIVE) {
				return null;
			}
		}
		
		drugQuantity.setStatus(EntityStatus.DELETED);
		return new DrugSearchDTO(drugQuantityRepo.save(drugQuantity));
	}
	
	@Override
	public List<DrugSearchDTO> searchDrugsInPharmacy(Long id, String name) {
		
		List<DrugQuantity> drugQuantities =  drugQuantityRepo.findByPharmacyIdAndStatusNotAndDrugNameContainsIgnoringCase(id, EntityStatus.DELETED, name);
		
		List<DrugSearchDTO> drugs = new ArrayList<>();
		
		for(DrugQuantity drugQ : drugQuantities) {
			drugs.add( new DrugSearchDTO( drugQ.getDrug().getId(),
					  					  drugQ.getDrug().getName(),
										  drugQ.getDrug().getCode(),
										  drugQ.getQuantity() )								
					 );
		}
		return drugs;
	}
	
	
	

	
	
	
}
