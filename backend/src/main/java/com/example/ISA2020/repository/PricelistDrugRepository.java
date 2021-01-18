package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.PricelistDrug;

public interface PricelistDrugRepository extends JpaRepository<PricelistDrug, Long> {
	
	PricelistDrug findOneById(Long id);
    
    List<PricelistDrug> findAll();
}
