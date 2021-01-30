package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.ConsultationReport;

public interface ConsultationReportRepository extends JpaRepository<ConsultationReport, Long>{
	
	ConsultationReport findOneById(Long id);
	
	List<ConsultationReport> findAll();
}
