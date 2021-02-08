package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Grade;

public interface GradeService {
	
	Grade findById(Long id);
	
	List<Grade> getAllConsultations();
}
