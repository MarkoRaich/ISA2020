package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.PricelistDrug;

public interface PricelistDrugService {
	
	PricelistDrug findById(Long id);
	
	//Pricelist createPricelist(PricelistDTO pricelistDTO);
	
	List<PricelistDrug> getAllPricelists();
}
