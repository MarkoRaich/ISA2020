package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
	
	Consultation findOneById(Long id);
	
	List<Consultation> findAll();
}
