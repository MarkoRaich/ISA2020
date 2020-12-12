package com.example.ISA2020.service;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.entity.Drug;

public interface DrugService {
	
	Drug findById(Long id);
    
	Drug findByName(String name);
	
	Drug createDrug(DrugDTO drugDTO);
}
