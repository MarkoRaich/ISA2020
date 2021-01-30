package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Examination;

public interface ExaminationService {
	
	Examination findById(Long id);
	
	List<Examination> getAllExaminations();
}
