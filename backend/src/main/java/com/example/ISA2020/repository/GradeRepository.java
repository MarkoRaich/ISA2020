package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long>{
	
	Grade findOneById(Long id);
	
	List<Grade> findAll();
	
	List<Grade> findByPatientId(Long id);
	
	List<Grade> findByDrugId(Long id);
	
	List<Grade> findByPharmacyId(Long id);
	
	List<Grade> findByDermatologistId(Long id);
	
	List<Grade> findByPharmacistId(Long id);
}
