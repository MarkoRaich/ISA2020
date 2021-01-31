package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.repository.ReservationRepository;
import com.example.ISA2020.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepo;

	@Override
	public Reservation findById(Long id) {
		// TODO Auto-generated method stub
		return reservationRepo.findOneById(id);
	}

	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return reservationRepo.findAll();
	}
	
	
}
