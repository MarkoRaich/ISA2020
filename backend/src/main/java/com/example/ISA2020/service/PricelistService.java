package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.PricelistDTO;
import com.example.ISA2020.entity.Pricelist;

public interface PricelistService {
	
	Pricelist findById(Long id);
	
	//Pricelist createPricelist(PricelistDTO pricelistDTO);
	
	List<Pricelist> getAllPricelists();
}
