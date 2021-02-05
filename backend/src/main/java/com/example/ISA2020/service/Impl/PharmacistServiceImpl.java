package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.PharmacistDTO;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.service.PharmacistService;

@Service
public class PharmacistServiceImpl implements PharmacistService{
	
	@Autowired
	private PharmacistRepository pharmacistRepo;
	
	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepo.findOneById(id);
	}

	@Override
	public Pharmacist findByUsername(String username) {
		return pharmacistRepo.findOneByUsername(username);
	}

	@Override
	public List<Pharmacist> getAllPharmacists() {
		return pharmacistRepo.findAll();
	}

	@Override
	public List<PharmacistDTO> findAllPharmacistsInPharmacy(Long id) {
		
		return convertToDTO(pharmacistRepo.findByPharmacyIdAndStatusNot(id, UserStatus.DELETED));
	}

	@Override
	public List<PharmacistDTO> searchPharmacistsInPharmacy(Long id, String firstName, String lastName) {
		return convertToDTO(pharmacistRepo.findByPharmacyIdAndStatusNotAndFirstNameContainsIgnoringCaseAndLastNameContainsIgnoringCase
										   (id, UserStatus.DELETED, firstName, lastName));
	}
	
	@Override
	public Pharmacist getPharmacist(Long id) {
		return pharmacistRepo.findByIdAndStatusNot(id, UserStatus.DELETED);
	}
	
		@Override
	public PharmacistDTO deletePharmacist(Long pharmacyId, Long id) {
		
		Pharmacist pharmacist = getPharmacist(id);
		
		if(pharmacist == null) {
			return null;
		}
		
		if(pharmacist.getPharmacy().getId() != pharmacyId) {
			return null;
		}
		
		pharmacist.setStatus(UserStatus.DELETED);
		return new PharmacistDTO(pharmacistRepo.save(pharmacist));
	}
		
	private List<PharmacistDTO> convertToDTO(List<Pharmacist> doctors) {
        List<PharmacistDTO> PharmacistDTOs = new ArrayList<>();
        for (Pharmacist pharmacist : doctors) {
        	PharmacistDTOs.add(new PharmacistDTO(pharmacist));
        }
        return PharmacistDTOs;
    }

	



	
}
