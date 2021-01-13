package com.example.ISA2020.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	Patient findByUsername(String username);

}
