package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.repository.ConsultationPriceRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.ConsultationPriceService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class ConsultationPriceServiceImpl implements ConsultationPriceService {

	@Autowired
	private ConsultationPriceRepository consultationPriceRepo;
	
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	
	
	@Override
	public ConsultationPrice findById(Long id) {
		return	consultationPriceRepo.findOneById(id);
	}

	@Override
	public List<ConsultationPrice> getAllConsultations() {
		return consultationPriceRepo.findAll();
	}

	//3.16 -------------------------------------- zakazivanje savetovanja
	@Override
	public List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByPriceForPharmacy(Long id) { //TREBA DODATI PROVERU PO DATUMU U KOM ZELI DA ZAKAZE
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		
		if(pharmacy == null) {
			return null;
		}
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByPharmacyIdOrderByPrice(id);
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		for(ConsultationPrice e : prices) {
			//if(e.getPharmacy().getId() == id) {
				if(e.getConsultation().getStatus() == ConsultationStatus.PREDEF_BOOKED) {
					ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
					dto.setConsultationName(e.getConsultation().getName());
					dto.setPrice(e.getPrice());
					dto.setPharmacyName(e.getPharmacy().getName());
					dto.setPharmacyRating(e.getPharmacy().getRating());
					dto.setPharmacyAddress(e.getPharmacy().getAddress());
					dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
					dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
					dtos.add(dto);
				}
			//}
		}
		
		return dtos;
	}
	
	
	@Override
	public List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByRatingForPharmacy(Long id) { //TREBA DODATI PROVERU PO DATUMU U KOM ZELI DA ZAKAZE
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		
		if(pharmacy == null) {
			return null;
		}
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByPharmacyIdOrderByPharmacyRating(id);
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		for(ConsultationPrice e : prices) {
			//if(e.getPharmacy().getId() == id) {
				if(e.getConsultation().getStatus() == ConsultationStatus.PREDEF_BOOKED) {
					ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
					dto.setConsultationName(e.getConsultation().getName());
					dto.setPrice(e.getPrice());
					dto.setPharmacyName(e.getPharmacy().getName());
					dto.setPharmacyRating(e.getPharmacy().getRating());
					dto.setPharmacyAddress(e.getPharmacy().getAddress());
					dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
					dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
					dtos.add(dto);
				}
			//}
		}
		
		return dtos;
	}
	
	@Override
	public List<PharmacistSimpleDTO> getAllPharmacistsSortedByRatingForPharmacy(Long id) {
		
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		
		if(pharmacy == null) {
			return null;
		}
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByPharmacyIdOrderByConsultationPharmacistRating(id);
		List<ConsultationPrice> predefPrices = new ArrayList<>();
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		for(ConsultationPrice e : prices) {
			//if(e.getPharmacy().getId() == id) {
				if(e.getConsultation().getStatus() == ConsultationStatus.PREDEF_BOOKED) {
					predefPrices.add(e);
				}
			//}
		}
		
		List<PharmacistSimpleDTO> pharmacists = new ArrayList<>();
		
		for(ConsultationPrice p : predefPrices) {
			PharmacistSimpleDTO dto = new PharmacistSimpleDTO();
			dto.setFirstName(p.getConsultation().getPharmacist().getFirstName());
			dto.setLastName(p.getConsultation().getPharmacist().getLastName());
			dto.setRating(p.getConsultation().getPharmacist().getRating());
			pharmacists.add(dto);
		}
		
		return pharmacists;
			
	}
	
	

	
	
	
	
	

}
