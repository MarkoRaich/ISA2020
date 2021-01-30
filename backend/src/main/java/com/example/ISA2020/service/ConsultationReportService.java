package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.ConsultationReport;

public interface ConsultationReportService {
	
	ConsultationReport findById(Long id);
	
	List<ConsultationReport> getAllConsultationReports();
}
