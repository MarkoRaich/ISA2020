package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Pricelist;

public interface PricelistDrugRepository extends JpaRepository<Pricelist, Long> {
	
	Pricelist findOneById(Long id);
    
    List<Pricelist> findAll();
}
