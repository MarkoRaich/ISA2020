package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.ConsultationPrice;

public interface ConsultationService {
	
	Consultation findById(Long id);
	
	List<Consultation> getAllConsultations();
	
	
}
