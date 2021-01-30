package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Examination;

public interface ExaminationRepository extends JpaRepository<Examination, Long>{

	Examination findOneById(Long id);
	
	List<Examination> findAll();
}
