package com.example.ISA2020.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.repository.ConsultationRepository;
import com.example.ISA2020.service.ConsultationService;

@Service
public class ConsultationServiceImpl implements ConsultationService{
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Override
	public Consultation findById(Long id) {
		return consultationRepository.findOneById(id);
	}

	@Override
	public List<Consultation> getAllConsultations() {
		return consultationRepository.findAll();
	}

	@Override
	public List<Consultation> getPharmacyConsultation(Long pharmId, LocalDateTime startDate, LocalDateTime endDate) {
		
		return consultationRepository.findByPharmacistPharmacyIdAndStatusAndIntervalStartDateTimeGreaterThanEqualAndIntervalEndDateTimeLessThanOrderByIntervalStartDateTime(pharmId, ConsultationStatus.DONE, startDate, endDate );

	}
	

}
