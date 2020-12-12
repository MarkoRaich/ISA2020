package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Drug;


public interface DrugRepository extends JpaRepository<Drug, Long> {
	
	Drug findOneById(Long id);
	
    Drug findOneByName(String name);
    
    Drug findOneByCode(String code);
    
    List<Drug> findAll();
    
}
