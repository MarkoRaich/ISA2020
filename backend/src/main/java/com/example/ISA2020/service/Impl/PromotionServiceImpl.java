package com.example.ISA2020.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.repository.PromotionRepository;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.PharmacyService;
import com.example.ISA2020.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	
	@Autowired
	private PromotionRepository promotionRepo;
	
	@Autowired
	private PharmacyService pharmacyService; 
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	
	
	

	@Override
	public Promotion findById(Long id) {
		return promotionRepo.findOneById(id);
	}

	@Override
	public List<Promotion> getAllPromotions() {
		return promotionRepo.findAll();
	}
	
	
	
	//Akcije i promocije
	@Override
	public List<PromotionDTO> getAllPromotionsDTO() {
		
		List<Promotion> promotions = promotionRepo.findAll();
		List<Promotion> patientPromotions = new ArrayList<>();
		List<PromotionDTO> dtos = new ArrayList<>();
	
		
//			//pretvaranje rezultata u dto modele
//		for(Promotion p : promotions) {
//			PromotionDTO dto = new PromotionDTO();
//			String idString = p.getId().toString();
//			Long id = Long.parseLong(idString);
//			dto.setId(id);
//			dto.setContent(p.getContent());
//			dto.setPharmacyName(p.getPharmacy().getName());
//			dto.setStartDate(p.getPeriod().getStartDateTime().toString());
//			dto.setEndDate(p.getPeriod().getEndDateTime().toString());
//
//			dtos.add(dto);
//		}
		
		return dtos;
	}

	@Override
	public PromotionDTO createPromotionSendEmail(@Valid PromotionDTO promotionDTO, PharmacyAdmin pharmacyAdmin) {

		LocalDate startDate = getDate(promotionDTO.getStartDateTime());
		LocalDate endDate = getDate(promotionDTO.getStartDateTime());
        LocalDateTime startDateTime = getLocalDateTime(startDate, promotionDTO.getStartDateTime());
        LocalDateTime endDateTime = getLocalDateTime(endDate, promotionDTO.getEndDateTime());
	
        if (startDateTime.isBefore(LocalDateTime.now()) || startDateTime.isAfter(endDateTime)) {
            return null;
        }
        DateTimeInterval dateTimeInterval = new DateTimeInterval(startDateTime, endDateTime);
        
		Promotion promotion = new Promotion(dateTimeInterval, promotionDTO.getContent(),pharmacyAdmin.getPharmacy());
		
		//slanje emaila svim pretplacenim pacijentima
		
		Set<Patient> subscribers = pharmacyAdmin.getPharmacy().getSubscribers();
		for(Patient sub : subscribers) {
			
			System.out.println("Slanje emaila korisniku: " + sub.getUsername());
			sendEmailPromotion(sub.getUsername(), promotion);
		}
		
		return new PromotionDTO(promotionRepo.save(promotion));
		
	}
	
	
	
	//pomocne metode
	@Async
	private void sendEmailPromotion(String email, Promotion promotion) {
		
		String subject = "Nova promocija u Vašoj apoteci: " + promotion.getPharmacy().getName();
		
		StringBuilder sb = new StringBuilder();
        sb.append("Postovani, posetite nas na našoj adresi " + promotion.getPharmacy().getAddress() + "\n");
        sb.append(" i iskoristite promociju " + promotion.getContent() + "\n");
        sb.append("Promocija važi od " + promotion.getPeriod().getStartDateTime() + " do " + promotion.getPeriod().getEndDateTime());
        sb.append(System.lineSeparator());
        String text = sb.toString();
		
        System.out.println(text);
        
		emailNotificationService.sendEmail(email, subject, text);
	}
	
	 private LocalDateTime getLocalDateTime(LocalDate date, String time) throws DateTimeParseException {
	        LocalTime localTime = LocalTime.parse(time.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
	        return LocalDateTime.of(date, localTime);
	    }

	private LocalDate getDate(String date) throws DateTimeParseException {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        return LocalDate.parse(date.substring(0, 10), formatter);
	    }
	
}
