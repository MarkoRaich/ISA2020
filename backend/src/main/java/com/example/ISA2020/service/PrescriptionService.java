package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Prescription;

public interface PrescriptionService {
	
	Prescription findById(Long id);
	
	List<Prescription> getAllPrescriptions();
}
