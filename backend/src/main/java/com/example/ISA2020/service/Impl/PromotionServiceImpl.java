package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.repository.PromotionRepository;
import com.example.ISA2020.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promotionRepo;

	@Override
	public Promotion findById(Long id) {
		return promotionRepo.findOneById(id);
	}

	@Override
	public List<Promotion> getAllPromotions() {
		return promotionRepo.findAll();
	}
	
	
	
	//Akcije i promocije
	@Override
	public List<PromotionDTO> getAllPromotionsDTO() {
		
		List<Promotion> promotions = promotionRepo.findAll();
		List<Promotion> patientPromotions = new ArrayList<>();
		List<PromotionDTO> dtos = new ArrayList<>();
	
		
			//pretvaranje rezultata u dto modele
		for(Promotion p : promotions) {
			PromotionDTO dto = new PromotionDTO();
			String idString = p.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setContent(p.getContent());
			dto.setPharmacyName(p.getPharmacy().getName());
			dto.setStartDate(p.getPeriod().getStartDateTime().toString());
			dto.setEndDate(p.getPeriod().getEndDateTime().toString());

			dtos.add(dto);
		}
		
		return dtos;
	}
	
}
