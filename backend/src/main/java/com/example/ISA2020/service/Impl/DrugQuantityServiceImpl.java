package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.compositeKeys.KeyDrugPharmacyQuantity;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.enumeration.EntityStatus;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.repository.DrugQuantityRepository;
import com.example.ISA2020.repository.DrugRepository;
import com.example.ISA2020.service.DrugQuantityService;

@Service
public class DrugQuantityServiceImpl implements DrugQuantityService {
	
	@Autowired
	private DrugQuantityRepository drugQuantityRepo;
	
	@Autowired
	private DrugRepository drugRepository;
	
	
	
	@Override
	public DrugQuantity findById(KeyDrugPharmacyQuantity id) {
		
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
	public List<DrugSearchDTO> findAllDrugsNotInPharmacy(Long pharmId) {
		
		List<DrugSearchDTO> drugsToReturn = new ArrayList<>();
		
		List<Drug> drugs = drugRepository.findAll(); //lista svih lekova
		
		List<DrugQuantity> drugQs = drugQuantityRepo.findByPharmacyIdAndStatus(pharmId, EntityStatus.ACTIVE); //lista svih kolicina lekova u apoteci
		
		for(Drug drug : drugs) {
			boolean isPresent = false;
			for(DrugQuantity drugQ : drugQs) {
				if(drugQ.getDrug().getId() == drug.getId()) {
					isPresent = true;
				}
			}
			if(!isPresent) {
				drugsToReturn.add(new DrugSearchDTO(drug.getId(), drug.getName(), drug.getCode(), 0 ));
			}
		}
		
		
		return drugsToReturn;
	}
	
	
	@Override
	public DrugSearchDTO deleteDrugFromPharmacy(Long pharmId, Long drugId) {
		
		DrugQuantity drugQuantity = drugQuantityRepo.findOneById(new KeyDrugPharmacyQuantity(pharmId, drugId));
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

	
	@Override
	public DrugSearchDTO addDrugInPharmacy(@Valid DrugSearchDTO drugDTO, Pharmacy pharmacy) {
		
		DrugQuantity deletedQuantity = drugQuantityRepo.findByPharmacyIdAndDrugId(pharmacy.getId(),drugDTO.getId()); //ako je bila pa je izbrisana onda cemo je aktivirati i azurirati kolicinu
		if(deletedQuantity == null) {
		
		Drug drug = drugRepository.findOneById(drugDTO.getId());
		
		DrugQuantity newDrugQ = new DrugQuantity(new KeyDrugPharmacyQuantity(), pharmacy, drug,drugDTO.getQuantity(), EntityStatus.ACTIVE);
		
		return new DrugSearchDTO(drugQuantityRepo.save(newDrugQ));
		}
		
		deletedQuantity.setQuantity(drugDTO.getQuantity());
		deletedQuantity.setStatus(EntityStatus.ACTIVE);
		
		return new DrugSearchDTO(drugQuantityRepo.save(deletedQuantity));
	}

	@Override
	public DrugSearchDTO changeDrugQuantityInPharmacy(@Valid DrugSearchDTO drugDTO, Pharmacy pharmacy) {
		
		DrugQuantity drugQ = drugQuantityRepo.findByPharmacyIdAndDrugId(pharmacy.getId(),drugDTO.getId());
		
		drugQ.setQuantity(drugDTO.getQuantity());
		
		
		return new DrugSearchDTO(drugQuantityRepo.save(drugQ));
	}


	
	
}
