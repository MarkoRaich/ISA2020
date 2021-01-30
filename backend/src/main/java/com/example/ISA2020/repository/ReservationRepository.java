package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	Reservation findOneById(Long id);
	
	List<Reservation> findAll();
}
