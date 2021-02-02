package com.example.ISA2020.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.repository.ExaminationRepository;
import com.example.ISA2020.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	
	@Autowired
	private ExaminationRepository examinationRepository;
	
	@Override
	public Examination findById(Long id) {
		// TODO Auto-generated method stub
		return examinationRepository.findOneById(id);
	}

	@Override
	public List<Examination> getAllExaminations() {
		// TODO Auto-generated method stub
		return examinationRepository.findAll();
	}

	@Override
	public List<Examination> getDermatologistUpcomingExaminations(Long dermId) {
		
		 return examinationRepository.findByDermatologistIdAndStatusNotAndIntervalEndDateTimeAfter(dermId, ExaminationStatus.CANCELED, LocalDateTime.now());
	}

}
