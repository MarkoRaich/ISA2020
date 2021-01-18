package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ISA2020.entity.PricelistDrug;
import com.example.ISA2020.repository.PricelistDrugRepository;
import com.example.ISA2020.service.PricelistDrugService;

public class PricelistDrugServiceImpl implements PricelistDrugService{
	
	@Autowired
	private PricelistDrugRepository pricelistDrugRepository;
	
	@Override
	public PricelistDrug findById(Long id) {
		return pricelistDrugRepository.findOneById(id);
	}

	/*
	 * @Override public Pricelist createPricelist(PricelistDTO pricelistDTO) {
	 * if(pricelistRepository.findOneById(pricelistDTO.getId()) }
	 */

	@Override
	public List<PricelistDrug> getAllPricelists() {
		return pricelistDrugRepository.findAll();
	}

}
