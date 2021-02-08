package com.example.ISA2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Grade;
import com.example.ISA2020.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {
	
	@Autowired
	private GradeRepository gradeRepo;
	
	@Override
	public Grade findById(Long id) {
		return gradeRepo.findOneById(id);
	}

	@Override
	public List<Grade> getAllConsultations() {
		return gradeRepo.findAll();
	}

}
