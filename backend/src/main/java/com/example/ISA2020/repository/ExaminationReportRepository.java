package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.ExaminationReport;

public interface ExaminationReportRepository extends JpaRepository<ExaminationReport, Long>{
	
	ExaminationReport findOneById(Long id);
	
	List<ExaminationReport> findAll();
}
