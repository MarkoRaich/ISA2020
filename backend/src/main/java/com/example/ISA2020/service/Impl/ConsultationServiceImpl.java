package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.repository.ConsultationRepository;
import com.example.ISA2020.service.ConsultationService;

public class ConsultationServiceImpl implements ConsultationService{
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Override
	public Consultation findById(Long id) {
		// TODO Auto-generated method stub
		return consultationRepository.findOneById(id);
	}

	@Override
	public List<Consultation> getAllConsultations() {
		// TODO Auto-generated method stub
		return consultationRepository.findAll();
	}

}