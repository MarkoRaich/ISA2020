package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.dto.DermatologistDTO;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.service.DermatologistService;
import com.example.ISA2020.service.ExaminationService;


@Service
public class DermatologistServiceImpl implements DermatologistService{
	
	@Autowired
	private DermatologistRepository dermatologistRepository;
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	private ExaminationService examinationService;
	
	
	
	
	@Override
	public Dermatologist findById(Long id) {
		return dermatologistRepository.findOneById(id);
	}

	@Override
	public Dermatologist findByUsername(String username) {
		return dermatologistRepository.findOneByUsername(username);
	}

	@Override
	public List<Dermatologist> getAllDermatologists() {
		return dermatologistRepository.findAll();
	}

	@Override
	public List<DermatologistDTO> findAllDermatologistsInPharmacy(Pharmacy pharmacy) {
		
		return convertToDTO(dermatologistRepository.findByPharmaciesIdAndStatusNot(pharmacy.getId(), UserStatus.DELETED));
	
	}

	@Override
	public DermatologistDTO deleteDermatologist(Long pharmacyId, Long id) {
		
		Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyId);
		Dermatologist dermatologist = getDermatologist(id);
		if(dermatologist == null) {
			return null;
		}
		if( !dermatologist.getPharmacies().contains(pharmacy) || HasExaminationsToDo(id) ){
			return null;
		}
		
		//dermatologist.setStatus(UserStatus.DELETED); //NE MOZE OVO JER MOZE U VISE APOTEKA BITI!!!
		
		
		pharmacy.getDermatologists().remove(dermatologist);
		dermatologist.getPharmacies().remove(pharmacy);
		
		return new DermatologistDTO(dermatologistRepository.save(dermatologist));
		
		
	}
	
	private boolean HasExaminationsToDo(Long dermId) {
		
		List<Examination> examinations = examinationService.getDermatologistUpcomingExaminations(dermId);
		
		if(examinations != null && !examinations.isEmpty()) {
			return true;
		}
		
		return false;
	}

	@Override
	public Dermatologist getDermatologist(Long id) {
		
		return dermatologistRepository.findOneById(id);
	}
	
	
	private List<DermatologistDTO> convertToDTO(List<Dermatologist> dermatologists){
		
		 List<DermatologistDTO> dermatologistDTOs = new ArrayList<>();
	        for (Dermatologist dermatologist : dermatologists) {
	        	dermatologistDTOs.add(new DermatologistDTO(dermatologist));
	        }
	        return dermatologistDTOs;
		
	}

	

	
}
