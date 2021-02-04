package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.EditPharmacyDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
    private PharmacyRepository pharmacyRepository;
	
	
	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findOneById(id);
	}

	@Override
	public Pharmacy findByName(String name) {
		return pharmacyRepository.findOneByName(name);
	}
	
	public Pharmacy findByAddress(String address) {
		return pharmacyRepository.findByAddressIgnoringCase(address);
	}

	@Override
	public Pharmacy createPharmacy(PharmacyDTO pharmacyDTO) {
		if (pharmacyRepository.findOneByName(pharmacyDTO.getName()) != null) {
            return null;
        } //findByName vraca null ako ga nadje
		
		Pharmacy newPharmacy = new Pharmacy(pharmacyDTO.getName(), pharmacyDTO.getAddress());
		
		return pharmacyRepository.save(newPharmacy);
	}

	@Override
	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepository.findAll();
	}

	@Override
	public EditPharmacyDTO edit(@Valid EditPharmacyDTO pharmacyDTO, Long pharmId) {
		
		Pharmacy existingPharmacy = pharmacyRepository.findOneById(pharmacyDTO.getId());

        if (existingPharmacy == null || existingPharmacy.getId() != pharmId) {
            return null;
        }

        Pharmacy pharmacyWithSameName = findByName(pharmacyDTO.getName());
        Pharmacy phamracyWithSameAddress = findByAddress(pharmacyDTO.getAddress());
        if ((pharmacyWithSameName != null && pharmacyWithSameName.getId() != existingPharmacy.getId())
                || (phamracyWithSameAddress != null && phamracyWithSameAddress.getId() != existingPharmacy.getId())) {

            return null;
        }
        existingPharmacy.setName(pharmacyDTO.getName());
        existingPharmacy.setAddress(pharmacyDTO.getAddress());
        existingPharmacy.setDescription(pharmacyDTO.getDescription());
        return new EditPharmacyDTO(pharmacyRepository.save(existingPharmacy));
        
	}
	
	
	
	// 3.9 -----------------------------------------------------------------------------------------
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyAddress(){
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByAddressAsc();
		
		List<DrugPricePharmacyNameAddressRatingDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO();
			dto.setPharmacyName(p.getName());
			dto.setPharmacyAddress(p.getAddress());
			dto.setPharmacyRating(p.getRating());
			
			pharmaciesDTO.add(dto);
		}
		
		return pharmaciesDTO;
	}
	
	
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyName(){
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByNameAsc();
		
		List<DrugPricePharmacyNameAddressRatingDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO();
			dto.setPharmacyName(p.getName());
			dto.setPharmacyAddress(p.getAddress());
			dto.setPharmacyRating(p.getRating());
			
			pharmaciesDTO.add(dto);
		}
		
		return pharmaciesDTO;
	}
	
	
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyRating() {
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByRatingAsc();
		
		List<DrugPricePharmacyNameAddressRatingDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO();
			dto.setPharmacyName(p.getName());
			dto.setPharmacyAddress(p.getAddress());
			dto.setPharmacyRating(p.getRating());
			
			pharmaciesDTO.add(dto);
		}
		
		return pharmaciesDTO;
	}
	//---------------------------------------------------------------------------------------
	

}
