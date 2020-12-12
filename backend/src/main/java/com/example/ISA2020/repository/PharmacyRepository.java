package com.example.ISA2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
	
	Pharmacy findOneById(Long id);
	
    Pharmacy findOneByName(String name);
}
