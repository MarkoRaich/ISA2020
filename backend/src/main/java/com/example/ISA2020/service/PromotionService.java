package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Promotion;

public interface PromotionService {
	
	Promotion findById(Long id);
	
	List<Promotion> getAllPromotions();
}
