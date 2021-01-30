package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{
	
	Prescription findOneById(Long id);
	
	List<Prescription> findAll();
}
