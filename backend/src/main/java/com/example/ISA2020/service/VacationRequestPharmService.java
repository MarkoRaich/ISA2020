package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.VacationRequestPharmDTO;
import com.example.ISA2020.entity.VacationRequestPharm;

public interface VacationRequestPharmService {
	
	VacationRequestPharm findById(Long id);
	
	List<VacationRequestPharm> getAllVacationRequestPharms();

	List<VacationRequestPharmDTO> getAllRequestsForPharmacy(Long id);

	VacationRequestPharmDTO approveRequestInPharmacy(Long pharmId, Long id);

	VacationRequestPharmDTO denyRequestInPharmacy(Long pharmId, Long id, String string);
}
