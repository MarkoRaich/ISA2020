package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.entity.ConsultationPrice;

public interface ConsultationPriceService {
	
	ConsultationPrice findById(Long id);
	
	List<ConsultationPrice> getAllConsultations();
	
}
