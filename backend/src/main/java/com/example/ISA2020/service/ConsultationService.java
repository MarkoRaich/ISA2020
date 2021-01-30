package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Consultation;

public interface ConsultationService {
	
	Consultation findById(Long id);
	
	List<Consultation> getAllConsultations();
}
