package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Prescription;
import com.example.ISA2020.repository.PrescriptionRepository;
import com.example.ISA2020.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{
	
	@Autowired
	private PrescriptionRepository prescriptionRepo;

	@Override
	public Prescription findById(Long id) {
		// TODO Auto-generated method stub
		return prescriptionRepo.findOneById(id);
	}

	@Override
	public List<Prescription> getAllPrescriptions() {
		// TODO Auto-generated method stub
		return prescriptionRepo.findAll();
	}
	
		
}
