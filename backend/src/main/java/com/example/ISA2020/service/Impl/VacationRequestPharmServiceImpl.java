package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.VacationRequestPharm;
import com.example.ISA2020.repository.VacationRequestPharmRepository;
import com.example.ISA2020.service.VacationRequestPharmService;

@Service
public class VacationRequestPharmServiceImpl implements VacationRequestPharmService{

	@Autowired
	private VacationRequestPharmRepository vacationPharmRepo;

	@Override
	public VacationRequestPharm findById(Long id) {
		// TODO Auto-generated method stub
		return vacationPharmRepo.findOneById(id);
	}

	@Override
	public List<VacationRequestPharm> getAllVacationRequestPharms() {
		// TODO Auto-generated method stub
		return vacationPharmRepo.findAll();
	}
}
