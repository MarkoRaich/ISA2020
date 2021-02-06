package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.repository.ConsultationPriceRepository;
import com.example.ISA2020.service.ConsultationPriceService;

@Service
public class ConsultationPriceServiceImpl implements ConsultationPriceService {

	@Autowired
	private ConsultationPriceRepository consultationPriceRepo;
	
	@Override
	public ConsultationPrice findById(Long id) {
		return	consultationPriceRepo.findOneById(id);
	}

	@Override
	public List<ConsultationPrice> getAllConsultations() {
		return consultationPriceRepo.findAll();
	}


}
