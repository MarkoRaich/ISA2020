package com.example.ISA2020.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	Patient findOneById(Long id);

	Patient findOneByUsername(String username);
	
	List<Patient> findAll();

}
