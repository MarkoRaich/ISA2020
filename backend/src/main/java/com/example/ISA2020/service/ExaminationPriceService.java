package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.entity.ExaminationPrice;

public interface ExaminationPriceService {
	
	ExaminationPrice findById(Long id);
	
	List<ExaminationPrice> getAllExaminations();
	
	//List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice();
	
	List<ExaminationPriceDermatologistDTO> getAllExaminationPricesSortedByPriceForPharmacy(Long id);
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByDermatologistRatingForPharmacy(Long id);
	
	ExaminationPriceDermatologistDTO makeExaminationReservation(Long examinationId);
	
	ExaminationPriceDermatologistDTO cancelExaminationReservation(Long examinationId);
	
	//3.9 ----------------------------------------------------- sortiranja razna
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice();
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByDate();
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByPriceBooked();
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByDateBooked();
}
