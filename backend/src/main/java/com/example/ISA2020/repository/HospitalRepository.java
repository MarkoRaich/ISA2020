package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long>{
	
	Hospital findOneById(Long id);
	
    Hospital findByName(String name);
    
    Hospital findByApiKey(String apiKey);
    
    List<Hospital> findAll();
    
    List<Hospital> findByCity(String city);
}
