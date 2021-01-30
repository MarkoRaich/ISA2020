package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.ExaminationReport;

public interface ExaminationReportService {

	ExaminationReport findById(Long id);
	
	List<ExaminationReport> getAllExaminationReports();
}
