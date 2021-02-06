package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.ExaminationPriceRepository;
import com.example.ISA2020.repository.ExaminationRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.ExaminationPriceService;

@Service
public class ExaminationPriceServiceImpl implements ExaminationPriceService {

	@Autowired
	private ExaminationPriceRepository examinationPriceRepo;
	
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	@Autowired
	private ExaminationRepository examinationRepo;
	
	@Autowired
	private DermatologistRepository dermatologistRepo;
	

	@Override
	public ExaminationPrice findById(Long id) {
		return examinationPriceRepo.findOneById(id);
	}

	@Override
	public List<ExaminationPrice> getAllExaminations() {
		return examinationPriceRepo.findAll();
	}
	
	//3.9 -------------------------------------------------------------
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice() {
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByPrice();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		for(ExaminationPrice e : prices) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getInterval().getStartDateTime());
			dto.setEndDateTime(e.getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	//3.13 ------------------------------------------------------------------------
	@Override
	public List<ExaminationPriceDermatologistDTO> getAllExaminationPricesSortedByPriceForPharmacy(Long id) {
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		
		if(pharmacy == null) {
			return null;
		}
		
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByPrice();
		List<ExaminationPriceDermatologistDTO> dtos = new ArrayList<>();
		
		for(ExaminationPrice e : prices) {
			if(e.getPharmacy().getId() == id) {
				if(e.getExamination().getStatus() == ExaminationStatus.PREDEF_BOOKED) {
					ExaminationPriceDermatologistDTO dto = new ExaminationPriceDermatologistDTO();
					dto.setExaminationName(e.getExamination().getName());
					dto.setDermatologistName(e.getExamination().getDermatologist().getFirstName());
					dto.setPrice(e.getPrice());
					dto.setDermatologistRating(e.getExamination().getDermatologist().getRating());
					dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
					dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
					dtos.add(dto);
				}
			}
		}
		
		return dtos;
	}
	
	
	//popraviti!!!!!!!   izmeni sta vraca, pronadji nacin kako da sortiras
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByDermatologistRatingForPharmacy(Long id) {
		//ExaminationPriceDermatologistDTO!!!
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		
		if(pharmacy == null) {
			return null;
		}
		
		List<Dermatologist> dermatologists = dermatologistRepo.findByOrderByRating();
		List<ExaminationPrice> prices = examinationPriceRepo.findAll();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		List<Examination> allExaminations = examinationRepo.findByOrderByDermatologistRating();
		
		
		for(Examination e : allExaminations) {
			//if(examinationPriceRepo.findByExaminationId(e.getId()) != null) {
				//if(pharmacyRepo.findOneById(id).getId() == (examinationPriceRepo.findByExaminationId(e.getId()).getPharmacy().getId())) {
					ExaminationPrice exam = examinationPriceRepo.findByExaminationId(e.getId());
					ExaminationPriceDTO dto = new ExaminationPriceDTO();
					dto.setExaminationName(exam.getExamination().getName());
					dto.setPharmacyName(exam.getPharmacy().getName());
					dto.setPrice(exam.getPrice());
					dto.setStartDateTime(exam.getExamination().getInterval().getStartDateTime());
					dto.setEndDateTime(exam.getExamination().getInterval().getEndDateTime());
					dtos.add(dto);
				//}
			//}
		}
		
		return dtos;
	}
	
	
	
}
