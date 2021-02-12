package com.example.ISA2020.service.Impl;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.PharmacistSimpleDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.DermPharmStatus;
import com.example.ISA2020.repository.ConsultationPriceRepository;
import com.example.ISA2020.repository.ConsultationRepository;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.ConsultationPriceService;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.PatientService;

@Service
public class ConsultationPriceServiceImpl implements ConsultationPriceService {

	@Autowired
	private ConsultationPriceRepository consultationPriceRepo;
	
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	@Autowired
	private PharmacistRepository pharmacistRepo;
	
	
	@Autowired
	private ConsultationRepository consultationRepo;
	
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
			/*String idString = e.getId().toString();
			Long id = Long.parseLong(idString); */
			dto.setConsultationId(e.getConsultation().getId());
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
			/*String idString = e.getId().toString();
			Long id = Long.parseLong(idString); */
			dto.setConsultationId(e.getConsultation().getId());
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
			/*String idString = e.getId().toString();
			Long id = Long.parseLong(idString); */
			dto.setConsultationId(e.getConsultation().getId());
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
			/*String idString = e.getId().toString();
			Long id = Long.parseLong(idString);*/
			dto.setConsultationId(e.getConsultation().getId());
			dto.setConsultationName(e.getConsultation().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
			dto.setStatus(e.getConsultation().getStatus().toString());
			dtos.add(dto);
		}
		
		return dtos; //provera da li je null??
	}

	//3.16 -------------------------------------- zakazivanje savetovanja
	@Override
	public List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByPriceForPharmacy(Long id, String startTime, String endTime) { //TREBA DODATI PROVERU PO DATUMU U KOM ZELI DA ZAKAZE
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		
		if(pharmacy == null) {
			return null;
		}
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByPharmacyIdOrderByPrice(id);
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		DateTimeInterval interval = new DateTimeInterval();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateStartTime = LocalDateTime.parse(startTime, formatter);
		LocalDateTime dateEndTime = LocalDateTime.parse(endTime, formatter);
		
		if(dateStartTime.isBefore(LocalDateTime.now()) || dateEndTime.isBefore(LocalDateTime.now())) {
			System.out.println("Ne moze pocetak ili kraj pregleda biti pre sadasnjeg vremena!");
			return null;
		} 
		
		
		for(ConsultationPrice e : prices) {
			//if(e.getPharmacy().getId() == id) {

				if(e.getConsultation().getStatus() == ConsultationStatus.AVAILABLE) {

					ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
					dto.setConsultationId(e.getConsultation().getId());
					dto.setConsultationName(e.getConsultation().getName());
					dto.setPrice(e.getPrice());
					dto.setPharmacyName(e.getPharmacy().getName());
					dto.setPharmacyRating(e.getPharmacy().getRating());
					dto.setPharmacyAddress(e.getPharmacy().getAddress());
					dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime().toString());
					dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime().toString());
					dto.setStatus(e.getConsultation().getStatus().toString());
			
					dtos.add(dto);
				}
			//}
		}
		if(dateStartTime.isAfter(dateEndTime)) {
			return dtos;			
		} else {
			return null;
		}
	}
	
	

	@Override
	public List<ConsultationPriceAddressDTO> getAllConsultationPricesSortedByRatingForPharmacy(Long id, String startTime, String endTime) { //TREBA DODATI PROVERU PO DATUMU U KOM ZELI DA ZAKAZE
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		
		if(pharmacy == null) {
			return null;
		}
		
		List<ConsultationPrice> prices = consultationPriceRepo.findByPharmacyIdOrderByPharmacyRating(id);
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		
		DateTimeInterval interval = new DateTimeInterval();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateStartTime = LocalDateTime.parse(startTime, formatter);
		LocalDateTime dateEndTime = LocalDateTime.parse(endTime, formatter);
		
		if(dateStartTime.isBefore(LocalDateTime.now()) || dateEndTime.isBefore(LocalDateTime.now())) {
			System.out.println("Ne moze pocetak ili kraj pregleda biti pre sadasnjeg vremena!");
			return null;
		} 
		
		
		for(ConsultationPrice e : prices) {
			//if(e.getPharmacy().getId() == id) {
				if(e.getConsultation().getStatus() == ConsultationStatus.BOOKED) {
					ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
					dto.setConsultationId(e.getConsultation().getId());
					dto.setConsultationName(e.getConsultation().getName());
					dto.setPrice(e.getPrice());
					dto.setPharmacyName(e.getPharmacy().getName());
					dto.setPharmacyRating(e.getPharmacy().getRating());
					dto.setPharmacyAddress(e.getPharmacy().getAddress());
					dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime().toString());
					dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime().toString());
					dto.setStatus(e.getConsultation().getStatus().toString());
					
					dtos.add(dto);
				}
			//}
		}
		
		if(dateStartTime.isAfter(dateEndTime)) {
			return dtos;			
		} else {
			return null;
		}
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
				if(e.getConsultation().getStatus() == ConsultationStatus.AVAILABLE) {
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
	
	
	
	//3.16  1.korak izlistavanje svih apoteka koje u datom terminu imaju slobodnog farmaceuta
	@Override
	public List<PharmacyDTO> getAllPharmaciesWithPharmacistForTime(String startTime, String endTime) { //slobodni farmaceut??

		Patient patient = patientService.getLoginPatient(); 
		if(patient == null) {
			return null;
		}
		
		/*Pharmacy pharmacy = pharmacyRepo.findOneById(pharmacyId);
		if(pharmacy == null) {
			return null;
		}*/
		
		String startTime2 = startTime;
		String target = "..";
		String replacement = " ";
		String startTimeProcessed = startTime2.replace(target, replacement);
		System.out.println(startTimeProcessed);

		
		String endTime2 = endTime;
		String endTimeProcessed = endTime2.replace(target, replacement);
		System.out.println(endTimeProcessed);

		
		List<ConsultationPrice> prices = consultationPriceRepo.findAll();
		
		DateTimeInterval interval = new DateTimeInterval();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateStartTime = LocalDateTime.parse(startTimeProcessed, formatter);
		LocalTime startTimeParsed = dateStartTime.toLocalTime();
		LocalDateTime dateEndTime = LocalDateTime.parse(endTimeProcessed, formatter);
		LocalTime endTimeParsed = dateEndTime.toLocalTime();
		
		
		
		List<Pharmacy> pharmacies = pharmacyRepo.findAll();
		List<PharmacyDTO> pharmaciesWithPharmacist = new ArrayList<>();
		for(Pharmacy p : pharmacies) {
			for(Pharmacist ph : p.getPharmacists()) {
				if(((ph.getWorkHoursFrom().isBefore(startTimeParsed)) && (ph.getWorkHoursTo().isAfter(endTimeParsed))) 
						|| ((ph.getWorkHoursFrom().equals(startTimeParsed)) && (ph.getWorkHoursTo().equals(endTimeParsed)))
						|| ((ph.getWorkHoursFrom().equals(startTimeParsed)) && (ph.getWorkHoursTo().isAfter(endTimeParsed)))
						|| ((ph.getWorkHoursFrom().isBefore(startTimeParsed)) && (ph.getWorkHoursTo().equals(endTimeParsed))) ) {
					
					/*Set<Consultation> cnts = ph.getConsultations();
					for(Consultation c : cnts) {
						//if((c.getInterval().getStartDateTime().isAfter(dateStartTime) && c.getInterval().getStartDateTime().isBefore(dateEndTime))) {
							if( (dateStartTime.isAfter(c.getInterval().getStartDateTime()) && dateStartTime.isBefore(c.getInterval().getEndDateTime()) ) 
									||  (dateEndTime.isBefore(c.getInterval().getStartDateTime()) && dateEndTime.isAfter(c.getInterval().getEndDateTime())  ) ) {
							c.getPharmacist().setWorkingStatus(DermPharmStatus.BUSY);
							pharmacistRepo.save(c.getPharmacist()); 
						}
					} */
					if(ph.getWorkingStatus() == DermPharmStatus.FREE) {
						List<ConsultationPrice> consultations = consultationPriceRepo.findByPharmacyId(ph.getPharmacy().getId());  //zasto vraca dva puta istu apoteku????
						for(ConsultationPrice con : consultations) {
							if(con == null) {
								System.out.println("consultation null");
							}
							if(con.getConsultation().getStatus() == ConsultationStatus.AVAILABLE) {
								if(con.getConsultation().getPharmacist().getId() == ph.getId()) {
									double price = con.getPrice();
									
									System.out.println("pharmacist id:" + ph.getId());
									System.out.println("pharmacy id:" + ph.getPharmacy().getId());
									
									PharmacyDTO dto = new PharmacyDTO();
									dto.setId(ph.getPharmacy().getId());
									dto.setName(ph.getPharmacy().getName());
									dto.setAddress(ph.getPharmacy().getAddress());
									dto.setDescription(ph.getPharmacy().getDescription());
									dto.setPharmacyRating(ph.getPharmacy().getRating());
									dto.setPrice(price);
									
									
									pharmaciesWithPharmacist.add(dto);
								}
							}
						}
					}
				}
			}
		}
		
		return pharmaciesWithPharmacist;
	}
	
	public double getPrice(Long id) {
		ConsultationPrice consultation = consultationPriceRepo.findOneById(id);
		double price = consultation.getPrice();
		return price;
	}
	
	//3.16   2.korak, izlistavaju se farmaceuti koji su slobodni u odabranoj apoteci
	@Override 
	public List<PharmacistSimpleDTO> getAllPharmacistFreeForPharmacy(Long id, String startTime, String endTime) {
		List<PharmacyDTO> pharmacies = getAllPharmaciesWithPharmacistForTime(startTime, endTime);
		
		DateTimeInterval interval = new DateTimeInterval();
		
		String startTime2 = startTime;
		String target = "..";
		String replacement = " ";
		String startTimeProcessed = startTime2.replace(target, replacement);
		System.out.println(startTimeProcessed);
		
		
		String endTime2 = endTime;
		//String target = "%20";
		//String replacement = " ";
		String endTimeProcessed = endTime2.replace(target, replacement);
		System.out.println(endTimeProcessed);

		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateStartTime = LocalDateTime.parse(startTimeProcessed, formatter);
		LocalTime startTimeParsed = dateStartTime.toLocalTime();
		LocalDateTime dateEndTime = LocalDateTime.parse(endTimeProcessed, formatter);
		LocalTime endTimeParsed = dateEndTime.toLocalTime();
		
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		if(pharmacy == null) {
			return null;
		}
		
		Set<Pharmacist> pharmacists = pharmacy.getPharmacists();
		List<PharmacistSimpleDTO> pharmacistsDTO = new ArrayList<>();
		
		for(Pharmacist ph : pharmacists) {
			if(((ph.getWorkHoursFrom().isBefore(startTimeParsed)) && (ph.getWorkHoursTo().isAfter(endTimeParsed))) 
					|| ((ph.getWorkHoursFrom().equals(startTimeParsed)) && (ph.getWorkHoursTo().equals(endTimeParsed)))
					|| ((ph.getWorkHoursFrom().equals(startTimeParsed)) && (ph.getWorkHoursTo().isAfter(endTimeParsed)))
					|| ((ph.getWorkHoursFrom().isBefore(startTimeParsed)) && (ph.getWorkHoursTo().equals(endTimeParsed))) ) {
						/*for(Consultation c : ph.getConsultations()) {
							//if((c.getInterval().getStartDateTime().isAfter(dateStartTime) && c.getInterval().getStartDateTime().isBefore(dateEndTime))) {
							if((dateStartTime.isAfter(c.getInterval().getStartDateTime()) && dateStartTime.isBefore(c.getInterval().getEndDateTime()) )
									|| (dateEndTime.isBefore(c.getInterval().getEndDateTime()) && dateEndTime.isAfter(c.getInterval().getStartDateTime())) ) {	
								c.getPharmacist().setWorkingStatus(DermPharmStatus.BUSY);
								pharmacistRepo.save(c.getPharmacist());
							}
						}*/
						if(ph.getWorkingStatus() == DermPharmStatus.FREE) {
							List<ConsultationPrice> consultations = consultationPriceRepo.findByPharmacyId(ph.getPharmacy().getId());  //zasto vraca dva puta istu apoteku????
							for(ConsultationPrice con : consultations) {
								if(con.getConsultation().getStatus() == ConsultationStatus.AVAILABLE) {
									if(con.getConsultation().getPharmacist().getId() == ph.getId()) {
										double price = con.getPrice();
										PharmacistSimpleDTO dto = new PharmacistSimpleDTO();
										System.out.println("pharmacist id:" + ph.getId());
										dto.setPharmacistId(ph.getId());
										dto.setFirstName(ph.getFirstName());
										dto.setLastName(ph.getLastName());
										dto.setRating(ph.getRating());
										dto.setPharmacyId(ph.getPharmacy().getId());
										
										pharmacistsDTO.add(dto);
									}
								}
							}
						}
					}
			}

		return pharmacistsDTO;
	}
	
	
	//3.16   3.korak, pacijent bira farmaceuta i kod njega zakazuje konsultacije
	@Override
	public ConsultationPriceAddressDTO makeConsultationReservation(Long pharmacistId, Long pharmacyId) {

		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			System.out.println("pacijent null");
			return null;
		} 
		
		Pharmacy pharmacy = pharmacyRepo.findOneById(pharmacyId);
		if(pharmacy == null) {
			return null;
		}
		
		//Pharmacy pharmacy = pharmacyRepo.findByPharmacistsId(pharmacistId);
		
		if(pharmacy == null) {
			System.out.println("Pharmacy null");
			return null;
		}

		
		List<ConsultationPrice> prices = consultationPriceRepo.findAll();
		List<ConsultationPrice> avaliablePrices = new ArrayList<>();
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		for(ConsultationPrice e : prices) {
			if(e.getPharmacy().getId() == pharmacy.getId()) {
				if(e.getConsultation().getStatus() == ConsultationStatus.AVAILABLE) {
					avaliablePrices.add(e);
				}
			}
		}
		
		List<PharmacistSimpleDTO> pharmacists = new ArrayList<>();
		
		String consultationName = null;
		String pharmacist = null;

		
		for(ConsultationPrice p : avaliablePrices) {
			if(patient.getPenalties() < 3) {
				if(p.getConsultation().getPharmacist().getId() == pharmacistId) {
					if(p.getPharmacy().getId() == pharmacy.getId()) {
						//ConsultationPrice consultation = consultationPriceRepo.findByPharmacyId(p.getPharmacy().getId());
						if(p.getConsultation().getPharmacist().getWorkingStatus() == DermPharmStatus.FREE) {
							if(p.getConsultation().getStatus() == ConsultationStatus.AVAILABLE) {
							//double price = consultation.getPrice();
							
								System.out.println("1");
								ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
								
								p.getConsultation().setStatus(ConsultationStatus.BOOKED);
								p.getConsultation().setPatient(patient);
								consultationPriceRepo.save(p);
								patient.getConsultations().add(p.getConsultation());
								patientService.save(patient);
								
				//				String idString = p.getId().toString();
				//				Long id = Long.parseLong(idString);
								dto.setConsultationId(p.getConsultation().getId());
								
								dto.setConsultationName(p.getConsultation().getName());
								dto.setPharmacyName(p.getPharmacy().getName());
								dto.setPharmacyAddress(p.getPharmacy().getAddress());
								dto.setPharmacyRating(p.getPharmacy().getRating());
								dto.setStartDateTime(p.getConsultation().getInterval().getStartDateTime().toString());
								dto.setEndDateTime(p.getConsultation().getInterval().getEndDateTime().toString());
								dto.setPrice(p.getPrice());
								dto.setStatus(p.getConsultation().getStatus().toString());
								
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
				
						        
						        emailNotificationService.sendEmail(/*patient.getUsername()*/ "dionizijm@gmail.com", subject, text);
						        
								return dto;
							}
						}
					}
				}
			}
		}

		return null;
				
	}
	
	//lista buducih konsultacija pacijenta
	@Override
	public List<ConsultationPriceAddressDTO> getAllConsultationsBooked() {
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Set<Consultation> consultations = patient.getConsultations();
		List<ConsultationPriceAddressDTO> consultationsDTO = new ArrayList<>();
		
		List<ConsultationPrice> prices = consultationPriceRepo.findAll();
		
		
		for(Consultation c : consultations) {
			if(c.getStatus() == ConsultationStatus.BOOKED) {
				ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
				
				//ConsultationPrice price = consultationPriceRepo.findOneById(c.getId());
				
				
				dto.setConsultationId(c.getId());
				
				dto.setConsultationName(c.getName());
				dto.setPharmacyName(c.getPharmacist().getPharmacy().getName());
				dto.setPharmacyAddress(c.getPharmacist().getPharmacy().getAddress());
				dto.setPharmacyRating(c.getPharmacist().getPharmacy().getRating());
				dto.setStartDateTime(c.getInterval().getStartDateTime().toString());
				dto.setEndDateTime(c.getInterval().getEndDateTime().toString());
				//dto.setPrice(price.getPrice());
				consultationsDTO.add(dto);
			}
		}
		
		for(ConsultationPrice p : prices) {
			for(ConsultationPriceAddressDTO c : consultationsDTO) {
				if(p.getConsultation().getId() == c.getConsultationId()) {
					c.setPrice(p.getPrice());
				}
			}
		}
		
		
		return consultationsDTO;
		
	}
	
	//3.18 otkazivanje savetovanja
	@Override
	public ConsultationPriceAddressDTO cancelConsultationReservation(Long consultationId) {

		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}

		
		LocalDateTime d = LocalDateTime.now();
		d = d.plusDays(1);
		
		List<ConsultationPrice> prices = consultationPriceRepo.findAll();
		List<ConsultationPrice> predefPrices = new ArrayList<>();
		List<ConsultationPriceAddressDTO> dtos = new ArrayList<>();
		
		Consultation consultation = consultationRepo.findOneById(consultationId);
		if(consultation.getStatus() == ConsultationStatus.BOOKED) {
			//consultation.setStatus(ConsultationStatus.CANCELED);
			if (d.isBefore(consultation.getInterval().getEndDateTime())) {
				consultation.setStatus(ConsultationStatus.AVAILABLE); //CANCELED???? PREDEF_BOOKED???
				//consultation.setPatient(null);
				consultationRepo.save(consultation);
				
				System.out.println("2");
				
				ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();

				dto.setConsultationId(consultation.getId());
				
				dto.setConsultationName(consultation.getName());
				dto.setPharmacyName(consultation.getPharmacist().getPharmacy().getName());
				dto.setPharmacyAddress(consultation.getPharmacist().getPharmacy().getAddress());
				dto.setPharmacyRating(consultation.getPharmacist().getPharmacy().getRating());
				dto.setStartDateTime(consultation.getInterval().getStartDateTime().toString());
				dto.setEndDateTime(consultation.getInterval().getEndDateTime().toString());
				//dto.setPrice(p.getPrice());
				dto.setStatus(consultation.getStatus().toString());
				
				String consultationName = consultation.getName();
				String pharmacist = consultation.getPharmacist().getFirstName();
				
				
				//salje se email na mejl pacijenta
				String subject = "Potvrda o otkazivanju savetovanja";
		        StringBuilder sb = new StringBuilder();
		        sb.append("Postovani, otkazali ste savetovanje: ");
		        sb.append(consultationName);
		        sb.append(" kod farmaceuta: ");
		        sb.append(pharmacist);
		        sb.append(System.lineSeparator());

		        String text = sb.toString();

		        
		        emailNotificationService.sendEmail(/*patient.getUsername()*/ "dionizijm@gmail.com", subject, text);
		        
				return dto;
			} else {
				int penalties = patient.getPenalties();
				int newPenalties = penalties + 1;
				patient.setPenalties(newPenalties);
				patientService.save(patient);
				System.out.println("penali: " + patient.getPenalties());
				
			}
		}
		return null;
		

				
	}

	@Override
	public List<ConsultationPriceAddressDTO> getAllConsultationsForPatient() {
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Set<Consultation> consultations = patient.getConsultations();
		List<ConsultationPriceAddressDTO> consultationsDTO = new ArrayList<>();
		
		List<ConsultationPrice> prices = consultationPriceRepo.findAll();
		
		
		for(Consultation c : consultations) {
			if(c.getStatus() == ConsultationStatus.DONE || c.getStatus() == ConsultationStatus.CANCELED) {
				ConsultationPriceAddressDTO dto = new ConsultationPriceAddressDTO();
				
				//ConsultationPrice price = consultationPriceRepo.findOneById(c.getId());
				
				
				dto.setConsultationId(c.getId());
				
				dto.setConsultationName(c.getName());
				dto.setPharmacyName(c.getPharmacist().getPharmacy().getName());
				dto.setPharmacyAddress(c.getPharmacist().getPharmacy().getAddress());
				dto.setPharmacyRating(c.getPharmacist().getPharmacy().getRating());
				dto.setStartDateTime(c.getInterval().getStartDateTime().toString());
				dto.setEndDateTime(c.getInterval().getEndDateTime().toString());
				//dto.setPrice(price.getPrice());
				consultationsDTO.add(dto);
			}
		}
		
		for(ConsultationPrice p : prices) {
			for(ConsultationPriceAddressDTO c : consultationsDTO) {
				if(p.getConsultation().getId() == c.getConsultationId()) {
					c.setPrice(p.getPrice());
				}
			}
		}
		
		
		return consultationsDTO;
	}
	
	

	
	
	
	
	

}
