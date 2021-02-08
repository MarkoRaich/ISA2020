package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	Reservation findOneById(Long id);
	
	List<Reservation> findAll();
	
	List<Reservation> findByPatientIdAndDrugId(Long patientId, Long drugId);
	
	List<Reservation> findByPatientIdAndPharmacyId(Long patientId, Long pharmacyId);
	
	//List<Reservation> findByPatientIdAndPharmacyDermatologistId(Long patientId, Long dermatologistId);
	
	//List<Reservation> findByPatientIdAndPharmacyPharmacistId(Long patientId, Long pharmacistId);
}
