package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.ExaminationReport;
import com.example.ISA2020.repository.ExaminationReportRepository;
import com.example.ISA2020.service.ExaminationReportService;

@Service
public class ExaminationReportServiceImpl implements ExaminationReportService {
	
	@Autowired
	private ExaminationReportRepository examinationReportRepo;
	
	@Override
	public ExaminationReport findById(Long id) {
		// TODO Auto-generated method stub
		return examinationReportRepo.findOneById(id);
	}

	@Override
	public List<ExaminationReport> getAllExaminationReports() {
		// TODO Auto-generated method stub
		return examinationReportRepo.findAll();
	}

}
