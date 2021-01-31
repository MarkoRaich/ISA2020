package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.DrugPrice;
import com.example.ISA2020.repository.DrugPriceRepository;
import com.example.ISA2020.service.DrugPriceService;

@Service
public class DrugPriceServiceImpl implements DrugPriceService {
	
	@Autowired
	private DrugPriceRepository drugPriceRepo;
	
	@Override
	public DrugPrice findById(Long id) {
		// TODO Auto-generated method stub
		return drugPriceRepo.findOneById(id);
	}

	@Override
	public List<DrugPrice> getAllDrugPrices() {
		// TODO Auto-generated method stub
		return drugPriceRepo.findAll();
	}
	
}
