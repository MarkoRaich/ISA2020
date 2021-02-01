package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
	
	Pharmacist findOneById(Long id);

	Pharmacist findOneByUsername(String username);
	
	List<Pharmacist> findAll();
}
