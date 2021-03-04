package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.dto.VacationRequestPharmDTO;
import com.example.ISA2020.entity.VacationRequestPharm;

public interface VacationRequestPharmRepository extends JpaRepository<VacationRequestPharm, Long>{
	
	VacationRequestPharm findOneById(Long id);
	
	List<VacationRequestPharm> findAll();

	List<VacationRequestPharm> findByPharmacistPharmacyId(Long id);
}
