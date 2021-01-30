package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.VacationRequestDerm;

public interface VacationRequestDermService {
	
	VacationRequestDerm findById(Long id);
	
	List<VacationRequestDerm> getAllVacationRequestDerms();
}
