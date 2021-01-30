package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.VacationRequestPharm;

public interface VcationRequestPharmRepository extends JpaRepository<VacationRequestPharm, Long>{
	
	VacationRequestPharm findOneById(Long id);
	
	List<VacationRequestPharm> findAll();
}
