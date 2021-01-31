package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.ConsultationReport;
import com.example.ISA2020.repository.ConsultationReportRepository;
import com.example.ISA2020.service.ConsultationReportService;

@Service
public class ConsultationReportServiceImpl implements ConsultationReportService {
	
	@Autowired
	private ConsultationReportRepository consultationReportRepository;
	
	@Override
	public ConsultationReport findById(Long id) {
		// TODO Auto-generated method stub
		return consultationReportRepository.findOneById(id);
	}

	@Override
	public List<ConsultationReport> getAllConsultationReports() {
		// TODO Auto-generated method stub
		return consultationReportRepository.findAll();
	}

}
