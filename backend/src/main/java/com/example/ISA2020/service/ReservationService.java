package com.example.ISA2020.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ISA2020.dto.DrugQuantityDTO;
import com.example.ISA2020.dto.ReservationDTO;
import com.example.ISA2020.entity.Reservation;

public interface ReservationService {

	Reservation findById(Long id);
	
	List<Reservation> getAllReservations();
	
	List<ReservationDTO> getAllReservationsActive();
	
	//ReservationDTO makeDrugReservation(Long pharmacyId, Long drugId, int quantity);
	
	List<DrugQuantityDTO> getAllDrugQuantities();
	
	ReservationDTO cancelDrugReservation(Long reservationId);

	ReservationDTO makeDrugReservation(Long pharmacyId, Long drugId, int quantity, String endTime);

	List<ReservationDTO> getCompletedReservationsForPharmacy(Long id);

	List<Reservation> getReservationsCompletedForPharmacy(Long pharmId);

	List<Reservation> getPharmacyReservations(Long id, LocalDateTime startDate, LocalDateTime endDate);
}
