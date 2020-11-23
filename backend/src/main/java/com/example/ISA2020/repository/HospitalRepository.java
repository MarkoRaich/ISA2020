package com.example.ISA2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Hospital;
import com.example.ISA2020.entity.NormalUser;

public interface HospitalRepository extends JpaRepository<Hospital, Long>{
	
	Hospital findOneById(Long id);
	
    Hospital findByName(String name);
    
    Hospital findByApiKey(String apiKey);
}
