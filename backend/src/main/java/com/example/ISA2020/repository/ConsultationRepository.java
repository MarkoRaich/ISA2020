package com.example.ISA2020.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.enumeration.ConsultationStatus;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
	
	Consultation findOneById(Long id);
	
	List<Consultation> findAll();

	List<Consultation> findByPharmacistPharmacyIdAndStatusAndIntervalStartDateTimeGreaterThanEqualAndIntervalEndDateTimeLessThanOrderByIntervalStartDateTime(
			Long pharmId, ConsultationStatus status, LocalDateTime startDate, LocalDateTime endDate);
}
