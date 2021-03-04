package com.example.ISA2020.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.AvailableExaminationDTO;
import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.PharmacyAdmin;

public interface ExaminationService {
	
	Examination findById(Long id);
	
	List<Examination> getAllExaminations();

	List<Examination> getDermatologistUpcomingExaminations(Long dermId);

	List<AvailableExaminationDTO> getAvailableExaminationsForPharmacy(Long id);

	ExaminationDTO createAvailableExamination(@Valid AvailableExaminationDTO availableExaminationDTO, PharmacyAdmin pharmacyAdmin);

	List<Examination> getTodaysExaminationsForDermatologist(Long id, LocalDateTime startDateTime);

	List<Examination> getFinishedExaminationsForPharmacy(Long pharmId);

	List<Examination> getPharmacyExaminations(Long pharmId, LocalDateTime startDate, LocalDateTime endDate);

	List<ExaminationPriceDTO> getAllBookedExaminationsForPatient(Patient patient);

	List<ExaminationPriceDermatologistDTO> getAllAvailableExaminations();

	List<ExaminationPriceDTO> getAllDoneExaminationsForPatient(Patient patient);


	
}
