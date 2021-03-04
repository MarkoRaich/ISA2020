package com.example.ISA2020.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.enumeration.ReservationStatus;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	Reservation findOneById(Long id);
	
	List<Reservation> findAll();
	
	List<Reservation> findByPatientIdAndDrugId(Long patientId, Long drugId);
	
	List<Reservation> findByPatientIdAndPharmacyId(Long patientId, Long pharmacyId);

	List<Reservation> findByPharmacyIdAndStatus(Long pharmId, ReservationStatus status);

	List<Reservation> findByPharmacyIdAndStatusAndIntervalStartDateTimeGreaterThanEqualAndIntervalEndDateTimeLessThanOrderByIntervalStartDateTime(
			Long pharmId, ReservationStatus status, LocalDateTime startDate, LocalDateTime endDate);
	
	//List<Reservation> findByPatientIdAndPharmacyDermatologistId(Long patientId, Long dermatologistId);
	
	//List<Reservation> findByPatientIdAndPharmacyPharmacistId(Long patientId, Long pharmacistId);
}
