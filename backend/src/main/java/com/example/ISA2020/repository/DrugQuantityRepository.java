package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.DrugQuantity;

public interface DrugQuantityRepository extends JpaRepository<DrugQuantity, Long> {

	DrugQuantity findOneById(Long id);
	
	List<DrugQuantity> findAll();
}
