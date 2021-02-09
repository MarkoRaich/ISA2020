package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.PharmacistDTO;
import com.example.ISA2020.dto.PharmacistSimpleDTO;
import com.example.ISA2020.entity.ConsultationPrice;

public interface ConsultationPriceService {
	
	ConsultationPrice findById(Long id);
	
	List<ConsultationPrice> getAllConsultations();
	
	List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByPriceForPharmacy(Long id);
	
	List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByRatingForPharmacy(Long id);
	
	List<PharmacistSimpleDTO> getAllPharmacistsSortedByRatingForPharmacy(Long id);
	
	//PharmacistSimpleDTO makeConsultationReservation(Long pharmacistId, Long pharmacyId);
	
	ConsultationPriceAddressDTO makeConsultationReservation(Long pharmacistId, Long pharmacyId);
	
	ConsultationPriceAddressDTO cancelConsultationReservation(Long pharmacistId, Long pharmacyId);
	
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByPrice();
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByDate();
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByPriceBooked();
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByDateBooked();
	
}
