package com.example.ISA2020.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.enumeration.ExaminationStatus;

public interface ExaminationRepository extends JpaRepository<Examination, Long>{

	Examination findOneById(Long id);
	
	List<Examination> findAll();

	List<Examination> findByDermatologistIdAndStatusNotAndIntervalEndDateTimeAfter(Long dermId, ExaminationStatus status, LocalDateTime localDateTime);

	//List<Examination> findByOrderByDateTimeInterval();
	
	List<Examination> findByOrderByDermatologistRating();

	List<Examination> findByPharmacyIdAndStatus(Long pharmId, ExaminationStatus available);

	List<Examination> findByDermatologistIdAndStatusNotAndIntervalStartDateTimeGreaterThanEqualAndIntervalStartDateTimeLessThan(
			Long dermId, ExaminationStatus canceled, LocalDateTime greater, LocalDateTime less);

	List<Examination> findByStatus(ExaminationStatus available);
	
	List<Examination> findByPatientIdAndStatus(Long patientId, ExaminationStatus booked);
}
 