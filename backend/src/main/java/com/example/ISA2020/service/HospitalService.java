package com.example.ISA2020.service;

import com.example.ISA2020.dto.HospitalDTO;
import com.example.ISA2020.entity.Hospital;

public interface HospitalService {
	
	Hospital findById(Long id);
    
	Hospital findOneByName(String name);
	
	Hospital createHospital(HospitalDTO hospitalDTO);
	
	Hospital findOneByApiKey(String apiKey);
}
