package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long>{

	Promotion findOneById(Long id);
	
	List<Promotion> findAll();
}
