package com.example.ISA2020.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.Dermatologist;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	
	Dermatologist findOneById(Long id);

	Dermatologist findOneByUsername(String username);
	
	List<Dermatologist> findAll();
}
