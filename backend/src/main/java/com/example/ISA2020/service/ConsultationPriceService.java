package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.service.Impl.PharmacistSimpleDTO;

public interface ConsultationPriceService {
	
	ConsultationPrice findById(Long id);
	
	List<ConsultationPrice> getAllConsultations();
	
	List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByPriceForPharmacy(Long id);
	
	List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByRatingForPharmacy(Long id);
	
	List<PharmacistSimpleDTO> getAllPharmacistsSortedByRatingForPharmacy(Long id);
	
	//PharmacistSimpleDTO makeConsultationReservation(Long pharmacistId, Long pharmacyId);
	
}
