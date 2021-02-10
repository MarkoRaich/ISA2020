package com.example.ISA2020.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.AvailableExaminationDTO;
import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.users.PharmacyAdmin;

public interface ExaminationService {
	
	Examination findById(Long id);
	
	List<Examination> getAllExaminations();

	List<Examination> getDermatologistUpcomingExaminations(Long dermId);

	List<AvailableExaminationDTO> getAvailableExaminationsForPharmacy(Long id);

	ExaminationDTO createAvailableExamination(@Valid AvailableExaminationDTO availableExaminationDTO, PharmacyAdmin pharmacyAdmin);

	List<Examination> getTodaysExaminationsForDermatologist(Long id, LocalDateTime startDateTime);
}
