package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.VacationRequestDerm;
import com.example.ISA2020.repository.VacationRequestDermRepository;
import com.example.ISA2020.service.VacationRequestDermService;

@Service
public class VacationRequestDermServiceImpl implements VacationRequestDermService{
	
	@Autowired
	private VacationRequestDermRepository vacationDermRepo;

	@Override
	public VacationRequestDerm findById(Long id) {
		// TODO Auto-generated method stub
		return vacationDermRepo.findOneById(id);
	}

	@Override
	public List<VacationRequestDerm> getAllVacationRequestDerms() {
		// TODO Auto-generated method stub
		return vacationDermRepo.findAll();
	}
	
	
}
