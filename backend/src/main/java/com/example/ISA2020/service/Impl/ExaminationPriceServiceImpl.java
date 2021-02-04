package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.repository.ExaminationPriceRepository;
import com.example.ISA2020.service.ExaminationPriceService;

@Service
public class ExaminationPriceServiceImpl implements ExaminationPriceService {

	@Autowired
	private ExaminationPriceRepository examinationPriceRepo;

	@Override
	public ExaminationPrice findById(Long id) {
		return examinationPriceRepo.findOneById(id);
	}

	@Override
	public List<ExaminationPrice> getAllExaminations() {
		return examinationPriceRepo.findAll();
	}
	
	//3.9 -------------------------------------------------------------
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice() {
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByPrice();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		for(ExaminationPrice e : prices) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getInterval().getStartDateTime());
			dto.setEndDateTime(e.getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
}
