package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Reservation;

public interface ReservationService {

	Reservation findById(Long id);
	
	List<Reservation> getAllReservations();
}
