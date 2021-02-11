package com.example.ISA2020.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.PharmacyExaminationKey;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.ExaminationPriceRepository;
import com.example.ISA2020.repository.ExaminationRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.ExaminationPriceService;
import com.example.ISA2020.service.PatientService;

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
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	
	

	@Override
	public ExaminationPrice findById(Long id) {
		return examinationPriceRepo.findOneById(id);
	}

	@Override
	public List<ExaminationPrice> getAllExaminations() {
		return examinationPriceRepo.findAll();
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
					dto.setExaminationId(e.getExamination().getId());
					dto.setExaminationName(e.getExamination().getExamType().getName());
					dto.setDermatologistName(e.getExamination().getDermatologist().getFirstName());
					dto.setPrice(e.getExamination().getExamType().getPrice());
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
					dto.setExaminationId(e.getId());
					dto.setExaminationName(exam.getExamination().getExamType().getName());
					dto.setPharmacyName(exam.getPharmacy().getName());
					dto.setPrice(exam.getExamination().getExamType().getPrice());
					dto.setStartDateTime(exam.getExamination().getInterval().getStartDateTime());
					dto.setEndDateTime(exam.getExamination().getInterval().getEndDateTime());
					dtos.add(dto);
				//}
			//}
		}
		
		return dtos;
	}
	
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		List<ExaminationPrice> prices = examinationPriceRepo.findAll();
		List<ExaminationPrice> patientExaminations = new ArrayList<>();
		
		for(ExaminationPrice e : prices) {
			if(e.getExamination().getPatient() != null && (e.getExamination().getPatient().getId() == patient.getId())) {
				if(e.getExamination().getStatus().equals(ExaminationStatus.DONE)) {
					patientExaminations.add(e);
				}
			}
		}
		
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		for(ExaminationPrice e : patientExaminations) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			/*PharmacyExaminationKey k = e.getId();    
			String idString = k.toString().toLowerCase();
			Long id = Long.parseLong(idString);*/
			dto.setExaminationId(e.getExamination().getId()); 
			dto.setExaminationName(e.getExamination().getExamType().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getExamination().getExamType().getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su pregledi zavrseni 3.9
	/*@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByPrice();
		List<ExaminationPrice> patientExaminations = new ArrayList<>();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		Set<Examination> examinations = patient.getExaminations();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		/*try {
			for(ExaminationPrice p : prices) {
				if(p.getExamination().getPatient() != null) {
					if(p.getExamination().getPatient() == patient) {
						System.out.println(p.getExamination().getStatus());
						if(p.getExamination().getStatus().equals(ExaminationStatus.DONE)) {
							patientExaminations.add(p);
						}
					}
				} else {
					System.out.println("skip");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
			//pretvaranje rezultata u dto modele
		for(ExaminationPrice e : prices) { //patientExaminations
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			String idString = e.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	} */
	
	//kada su pregledi zavrseni
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByDate() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByIntervalStartDateTime();
		List<ExaminationPrice> patientExaminations = new ArrayList<>();
		List<ExaminationPrice> sortiranoPoDatumu = new ArrayList<>();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		List<String> datumiString = new ArrayList();
		
		
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ExaminationPrice p : prices) {
			if(p.getExamination().getPatient().getId() == patient.getId()) {
				System.out.println(p.getExamination().getStatus());
				if(p.getExamination().getStatus().equals(ExaminationStatus.DONE)) {
					patientExaminations.add(p);
				}
			}
		}
		
		for(ExaminationPrice p : patientExaminations) {
			String date = p.getInterval().getStartDateTime().toString();
			datumiString.add(date);
		}
		
			//pretvaranje rezultata u dto modele
		for(ExaminationPrice e : patientExaminations) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			/*String idString = e.getId().toString();
			Long id = Long.parseLong(idString); */
			dto.setExaminationId(e.getExamination().getId());
			dto.setExaminationName(e.getExamination().getExamType().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getExamination().getExamType().getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su pregledi zakazani
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPriceBooked() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByPrice();
		List<ExaminationPrice> patientExaminations = new ArrayList<>();
		List<ExaminationPriceDTO> dtos = new ArrayList<>();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(ExaminationPrice p : prices) {
			if(p.getExamination().getPatient().getId() == patient.getId()) {
				System.out.println(p.getExamination().getStatus());
				if(p.getExamination().getStatus().equals(ExaminationStatus.BOOKED)) {
					patientExaminations.add(p);
				}
			}
		}
		
			//pretvaranje rezultata u dto modele
		for(ExaminationPrice e : patientExaminations) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			/*String idString = e.getId().toString();
			Long id = Long.parseLong(idString);*/
			dto.setExaminationId(e.getExamination().getId());
			dto.setExaminationName(e.getExamination().getExamType().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getExamination().getExamType().getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su pregledi zakazani
	@Override
	public List<ExaminationPriceDermatologistDTO> getAllExaminationsAvailable() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		//List<ExaminationPrice> prices = examinationPriceRepo.findByOrderByIntervalStartDateTime();
		List<Examination> examinations = examinationRepo.findByStatus(ExaminationStatus.AVAILABLE);
		List<ExaminationPriceDermatologistDTO> dtos = new ArrayList<>();
		
		List<String> datumiString = new ArrayList();
		
		for(Examination e : examinations) {
			//if(e.getPatient().getId() == patient.getId()) {
				ExaminationPriceDermatologistDTO dto = new ExaminationPriceDermatologistDTO();

				dto.setExaminationId(e.getId());
				dto.setExaminationName(e.getExamType().getName());
				dto.setDermatologistName(e.getDermatologist().getFirstName() + " " + e.getDermatologist().getLastName());
				dto.setDermatologistRating(e.getDermatologist().getRating());
				dto.setPrice(e.getExamType().getPrice());
				dto.setStartDateTime(e.getInterval().getStartDateTime());
				dto.setEndDateTime(e.getInterval().getEndDateTime());
				dtos.add(dto);
			//}
		}
		
		return dtos;
		
	}
	
	
	//3.13 zakazivanje rezervacije pregleda
	@Override
	public ExaminationPriceDermatologistDTO makeExaminationReservation(Long examinationId) {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		Examination examination = examinationRepo.findOneById(examinationId);
		
		if(examination == null) {
			return null;
		}
		
		
		
		if(examination.getStatus() == ExaminationStatus.AVAILABLE) {
			if(patient.getPenalties() < 3) {
				//System.out.println("2");
				examination.setStatus(ExaminationStatus.BOOKED);
				examination.setPatient(patient);
				examinationRepo.save(examination);
				//examinationRepo.save(examination.getExamination());
				
				ExaminationPriceDermatologistDTO dto = new ExaminationPriceDermatologistDTO();
				
				/*String idString = examination.getId().toString();
				Long id = Long.parseLong(idString); */
				dto.setExaminationId(examination.getId());
				
				dto.setDermatologistName(examination.getDermatologist().getFirstName() + " " + examination.getDermatologist().getLastName());
				dto.setDermatologistRating(examination.getDermatologist().getRating());
				dto.setPrice(examination.getExamType().getPrice());
				dto.setExaminationName(examination.getExamType().getName());
				dto.setStartDateTime(examination.getInterval().getStartDateTime());
				dto.setEndDateTime(examination.getInterval().getEndDateTime());
				
				//salje se email na mejl pacijenta
				composeAndSendEmail(/*patient.getUsername()*/"dionizijm@gmail.com", dto.getExaminationName(), dto.getDermatologistName());
				
				return dto;
			}
		}
		//System.out.println("3");
		return null;
		
	}
	
	
    private void composeAndSendEmail(String recipientEmail, String examinationName, String dermatologistName) {
        String subject = "Potvrda o zakazivanju pregleda";
        StringBuilder sb = new StringBuilder();
        sb.append("Postovani, zakazali ste pregled: ");
        sb.append(examinationName);
        sb.append(" kod dermatologa: ");
        sb.append(dermatologistName);
        sb.append(System.lineSeparator());

        String text = sb.toString();

        emailNotificationService.sendEmail(recipientEmail, subject, text);
    }
    
    //3.15 Otkazivanje pregleda ----------------------------------------------------------
	@Override
	public ExaminationPriceDermatologistDTO cancelExaminationReservation(Long examinationId) {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		Examination examination = examinationRepo.findOneById(examinationId);
		
		if(examination == null) {
			return null;
		}
		
		LocalDateTime d = LocalDateTime.now();
		d = d.plusDays(1);
		
		
		if(examination.getStatus() == ExaminationStatus.BOOKED) {
			//if( )   PROVERA 24H!!!!
			if (d.isBefore(examination.getInterval().getEndDateTime())) {
	 			//System.out.println("2");
				examination.setStatus(ExaminationStatus.CANCELED); //CANCELED???? PREDEF_BOOKED???
				examinationRepo.save(examination);
				//examinationRepo.save(examination.getExamination());
				
				
				ExaminationPriceDermatologistDTO dto = new ExaminationPriceDermatologistDTO();
	
				dto.setExaminationId(examination.getId());
				
				dto.setDermatologistName(examination.getDermatologist().getFirstName());
				dto.setDermatologistRating(examination.getDermatologist().getRating());
				dto.setPrice(examination.getExamType().getPrice());
				dto.setExaminationName(examination.getExamType().getName());
				dto.setStartDateTime(examination.getInterval().getStartDateTime());
				dto.setEndDateTime(examination.getInterval().getEndDateTime());
				
				
				//salje se email na mejl pacijenta
				String subject = "Potvrda o otkazivanju pregleda";
		        StringBuilder sb = new StringBuilder();
		        sb.append("Postovani, otkazali ste pregled: ");
		        sb.append(dto.getExaminationName());
		        sb.append(" kod dermatologa: ");
		        sb.append(dto.getDermatologistName());
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
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByDateBooked() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
