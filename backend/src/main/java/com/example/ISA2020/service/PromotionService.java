package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.entity.users.PharmacyAdmin;

public interface PromotionService {
	
	Promotion findById(Long id);
	
	List<Promotion> getAllPromotions();
	
	List<PromotionDTO> getAllPromotionsDTO();

	PromotionDTO createPromotionSendEmail(@Valid PromotionDTO promotionDTO, PharmacyAdmin pharmacyAdmin);
}
