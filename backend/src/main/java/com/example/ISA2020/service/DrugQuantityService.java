package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.DrugQuantity;

public interface DrugQuantityService {
	
	DrugQuantity findById(Long id);
	
	List<DrugQuantity> getAllDrugQuantities();
}
