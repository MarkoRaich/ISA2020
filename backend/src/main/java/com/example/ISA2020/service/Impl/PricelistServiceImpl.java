package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ISA2020.dto.PricelistDTO;
import com.example.ISA2020.entity.Pricelist;
import com.example.ISA2020.repository.PricelistRepository;
import com.example.ISA2020.service.PricelistService;

public class PricelistServiceImpl implements PricelistService{
	
	@Autowired
	private PricelistRepository pricelistRepository;
	
	@Override
	public Pricelist findById(Long id) {
		return pricelistRepository.findOneById(id);
	}

	/*
	 * @Override public Pricelist createPricelist(PricelistDTO pricelistDTO) {
	 * if(pricelistRepository.findOneById(pricelistDTO.getId()) }
	 */

	@Override
	public List<Pricelist> getAllPricelists() {
		return pricelistRepository.findAll();
	}

}
