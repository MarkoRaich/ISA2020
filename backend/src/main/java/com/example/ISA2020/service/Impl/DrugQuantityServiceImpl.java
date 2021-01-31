package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.repository.DrugQuantityRepository;
import com.example.ISA2020.service.DrugQuantityService;

@Service
public class DrugQuantityServiceImpl implements DrugQuantityService {
	
	@Autowired
	private DrugQuantityRepository drugQuantityRepo;
	
	@Override
	public DrugQuantity findById(Long id) {
		// TODO Auto-generated method stub
		return drugQuantityRepo.findOneById(id);
	}

	@Override
	public List<DrugQuantity> getAllDrugQuantities() {
		// TODO Auto-generated method stub
		return drugQuantityRepo.findAll();
	}

}
