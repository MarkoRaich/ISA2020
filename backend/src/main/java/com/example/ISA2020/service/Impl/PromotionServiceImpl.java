package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.repository.PromotionRepository;
import com.example.ISA2020.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promotionRepo;

	@Override
	public Promotion findById(Long id) {
		// TODO Auto-generated method stub
		return promotionRepo.findOneById(id);
	}

	@Override
	public List<Promotion> getAllPromotions() {
		// TODO Auto-generated method stub
		return promotionRepo.findAll();
	}
	
	
}
