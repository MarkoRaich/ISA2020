package com.example.ISA2020.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ISA2020.entity.VacationRequestDerm;
import com.example.ISA2020.entity.users.Dermatologist;

public interface VacationRequestDermService {
	
	VacationRequestDerm findById(Long id);
	
	List<VacationRequestDerm> getAllVacationRequestDerms();

	boolean isDermatologistOnVacation(Dermatologist dermatologist, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
