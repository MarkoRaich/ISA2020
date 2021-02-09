package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.dto.PharmacistSimpleDTO;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.repository.ConsultationPriceRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.ConsultationPriceService;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.PatientService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class ConsultationPriceServiceImpl implements ConsultationPriceService {

	@Autowired
	private ConsultationPriceRepository consultationPriceRepo;
	
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	
	
	@Override
	public ConsultationPrice findById(Long id) {
		return	consultationPriceRepo.findOneById(id);
	}

	@Override
	public List<ConsultationPrice> getAllConsultations() {
		return consultationPriceRepo.findAll();
	}
	
	
	//3.9 za Savetovanja -------------------------------------------------------------------
	@Override
	public List<ConsultationPriceDTO> getAllConsultationPricesSortedByPrice() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByOrderByPrice();
		List<ConsultationPrice> patientConsultations = new ArrayList<>();
		List<ConsultationPriceDTO> dtos = new ArrayList<>();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ConsultationPrice p : prices) {
			if(p.getConsultation().getPatient().getId() == patient.getId()) {
				//System.out.println(p.getExamination().getStatus());
				if(p.getConsultation().getStatus().equals(ConsultationStatus.DONE)) {
					patientConsultations.add(p);
				}
			}
		}
		
			//pretvaranje rezultata u dto modele
		for(ConsultationPrice e : patientConsultations) {
			ConsultationPriceDTO dto = new ConsultationPriceDTO();
			String idString = e.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setConsultationName(e.getConsultation().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	@Override
	public List<ConsultationPriceDTO> getAllConsultationPricesSortedByDate() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByOrderByIntervalStartDateTime();
		List<ConsultationPrice> patientConsultations = new ArrayList<>();
		List<ConsultationPrice> sortiranoPoDatumu = new ArrayList<>();
		List<ConsultationPriceDTO> dtos = new ArrayList<>();
		
		List<String> datumiString = new ArrayList();
		
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ConsultationPrice p : prices) {
			if(p.getConsultation().getPatient().getId() == patient.getId()) {
				//System.out.println(p.getExamination().getStatus());
				if(p.getConsultation().getStatus().equals(ConsultationStatus.DONE)) {
					patientConsultations.add(p);
				}
			}
		}
		
		for(ConsultationPrice p : patientConsultations) {
			String date = p.getInterval().getStartDateTime().toString();
			datumiString.add(date);
		}
		

			//pretvaranje rezultata u dto modele
		for(ConsultationPrice e : patientConsultations) {
			ConsultationPriceDTO dto = new ConsultationPriceDTO();
			String idString = e.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setConsultationName(e.getConsultation().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su savetovanja zakazana
	@Override
	public List<ConsultationPriceDTO> getAllConsultationPricesSortedByPriceBooked() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByOrderByPrice();
		List<ConsultationPrice> patientConsultations = new ArrayList<>();
		List<ConsultationPriceDTO> dtos = new ArrayList<>();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ConsultationPrice p : prices) {
			if(p.getConsultation().getPatient().getId() == patient.getId()) {
				//System.out.println(p.getExamination().getStatus());
				if(p.getConsultation().getStatus().equals(ConsultationStatus.BOOKED)) {
					patientConsultations.add(p);
				}
			}
		}
		
			//pretvaranje rezultata u dto modele
		for(ConsultationPrice e : patientConsultations) {
			ConsultationPriceDTO dto = new ConsultationPriceDTO();
			String idString = e.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setConsultationName(e.getConsultation().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su savetovanja zakazana
	@Override
	public List<ConsultationPriceDTO> getAllConsultationPricesSortedByDateBooked() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByOrderByIntervalStartDateTime();
		List<ConsultationPrice> patientConsultations = new ArrayList<>();
		List<ConsultationPrice> sortiranoPoDatumu = new ArrayList<>();
		List<ConsultationPriceDTO> dtos = new ArrayList<>();
		
		List<String> datumiString = new ArrayList();
		
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ConsultationPrice p : prices) {
			if(p.getConsultation().getPatient().getId() == patient.getId()) {
				//System.out.println(p.getExamination().getStatus());
				if(p.getConsultation().getStatus().equals(ConsultationStatus.BOOKED)) {
					patientConsultations.add(p);
				}
			}
		}
		
		for(ConsultationPrice p : patientConsultations) {
			String date = p.getInterval().getStartDateTime().toString();
			datumiString.add(date);
		}
		

			//pretvaranje rezultata u dto modele
		for(ConsultationPrice e : patientConsultations) {
			ConsultationPriceDTO dto = new ConsultationPriceDTO();
			String idString = e.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setConsultationName(e.getConsultation().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
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
	
	
	
	//3.16
	@Override
	public ConsultationPriceAddressDTO makeConsultationReservation(Long pharmacistId, Long pharmacyId) {

		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Pharmacy pharmacy = pharmacyRepo.findOneById(pharmacyId);
		if(pharmacy == null) {
			return null;
		}
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByPharmacyIdOrderByConsultationPharmacistRating(pharmacyId);
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
		
		String consultationName = null;
		String pharmacist = null;

		ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
		
		for(ConsultationPrice p : predefPrices) {
			if(p.getConsultation().getPharmacist().getId() == pharmacistId) {
				p.getConsultation().setStatus(ConsultationStatus.BOOKED);
				p.getConsultation().setPatient(patient);
				consultationPriceRepo.save(p);
				
				String idString = p.getId().toString();
				Long id = Long.parseLong(idString);
				dto.setId(id);
				
				dto.setConsultationName(p.getConsultation().getName());
				dto.setPharmacyName(p.getPharmacy().getName());
				dto.setPharmacyAddress(p.getPharmacy().getAddress());
				dto.setPharmacyRating(p.getPharmacy().getRating());
				dto.setStartDateTime(p.getConsultation().getInterval().getStartDateTime());
				dto.setEndDateTime(p.getConsultation().getInterval().getEndDateTime());
				dto.setPrice(p.getPrice());
				
				consultationName = p.getConsultation().getName();
				pharmacist = p.getConsultation().getPharmacist().getFirstName();
				
				
				//salje se email na mejl pacijenta
				String subject = "Potvrda o zakazivanju savetovanja";
		        StringBuilder sb = new StringBuilder();
		        sb.append("Postovani, zakazali ste savetovanje: ");
		        sb.append(consultationName);
		        sb.append(" kod farmaceuta: ");
		        sb.append(pharmacist);
		        sb.append(System.lineSeparator());

		        String text = sb.toString();

		        
		        emailNotificationService.sendEmail(/*patient.getUsername()*/ "", subject, text);
		        
				return dto;
			}
		}
		
	
		
		return null;
				
	}
	
	//3.18
	@Override
	public ConsultationPriceAddressDTO cancelConsultationReservation(Long pharmacistId, Long pharmacyId) {

		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Pharmacy pharmacy = pharmacyRepo.findOneById(pharmacyId);
		if(pharmacy == null) {
			return null;
		}
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByPharmacyIdOrderByConsultationPharmacistRating(pharmacyId);
		List<ConsultationPrice> predefPrices = new ArrayList<>();
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		for(ConsultationPrice e : prices) {
			//if(e.getPharmacy().getId() == id) {
				if(e.getConsultation().getStatus() == ConsultationStatus.BOOKED) {
					predefPrices.add(e);
				}
			//}
		}
		
		List<PharmacistSimpleDTO> pharmacists = new ArrayList<>();
		
		String consultationName = null;
		String pharmacist = null;

		ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
		
		for(ConsultationPrice p : predefPrices) {
			if(p.getConsultation().getPharmacist().getId() == pharmacistId) {
				p.getConsultation().setStatus(ConsultationStatus.CANCELED); //CANCELED???? PREDEF_BOOKED???
				p.getConsultation().setPatient(null);
				consultationPriceRepo.save(p);
				
				String idString = p.getId().toString();
				Long id = Long.parseLong(idString);
				dto.setId(id);
				
				dto.setConsultationName(p.getConsultation().getName());
				dto.setPharmacyName(p.getPharmacy().getName());
				dto.setPharmacyAddress(p.getPharmacy().getAddress());
				dto.setPharmacyRating(p.getPharmacy().getRating());
				dto.setStartDateTime(p.getConsultation().getInterval().getStartDateTime());
				dto.setEndDateTime(p.getConsultation().getInterval().getEndDateTime());
				dto.setPrice(p.getPrice());
				
				consultationName = p.getConsultation().getName();
				pharmacist = p.getConsultation().getPharmacist().getFirstName();
				
				
				//salje se email na mejl pacijenta
				String subject = "Potvrda o otkazivanju savetovanja";
		        StringBuilder sb = new StringBuilder();
		        sb.append("Postovani, otkazali ste savetovanje: ");
		        sb.append(consultationName);
		        sb.append(" kod farmaceuta: ");
		        sb.append(pharmacist);
		        sb.append(System.lineSeparator());

		        String text = sb.toString();

		        
		        emailNotificationService.sendEmail(/*patient.getUsername()*/ "", subject, text);
		        
				return dto;
			}
		}
			
		return null;
				
	}
	
	

	
	
	
	
	

}
