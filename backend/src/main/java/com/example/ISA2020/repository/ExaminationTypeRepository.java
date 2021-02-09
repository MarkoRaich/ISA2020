package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.ExaminationType;

public interface ExaminationTypeRepository extends JpaRepository<ExaminationType, Long>{

	List<ExaminationType> findByPharmacyId(Long pharmId);
	
	ExaminationType findOneById(Long id);

}
