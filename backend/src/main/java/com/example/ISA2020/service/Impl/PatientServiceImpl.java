package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.dto.PatientWithIdDTO;
import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.dto.ReservationDTO;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.repository.ConsultationPriceRepository;
import com.example.ISA2020.repository.DrugPriceRepository;
import com.example.ISA2020.repository.DrugRepository;
import com.example.ISA2020.repository.ExaminationPriceRepository;
import com.example.ISA2020.repository.ExaminationRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.repository.PromotionRepository;
import com.example.ISA2020.repository.ReservationRepository;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired 
	private DrugRepository drugRepo;
	
	@Autowired 
	private PharmacyRepository pharmacyRepo;
	
	@Autowired 
	private DrugPriceRepository drugPriceRepo;
	
	@Autowired
	private ExaminationPriceRepository examinationPriceRepo;
	
	@Autowired
	private ExaminationRepository examinationRepo;
	
	@Autowired
	private ConsultationPriceRepository consultationPriceRepo;
		
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private PromotionRepository promotionRepo;
	
    @Autowired
    private EmailNotificationService emailNotificationService;
	
	

	@Override
	public Patient findById(Long id) {
		return patientRepo.findOneById(id);
	}

	@Override
	public Patient findByUsername(String username) {
		return patientRepo.findOneByUsername(username);
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepo.findAll();
	}
	
	
	@Override
    public Patient getLoginPatient() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            Patient patient = patientRepo.findOneByUsername(currentUser.getName());
            if (patient != null) {
                return patient;
            }
        } catch (UsernameNotFoundException ex) {

        }
        return null;
    }

	private List<PatientWithIdDTO> convertToDTO(List<Patient> patients) {
        if (patients == null || patients.isEmpty()) {
            return new ArrayList<>();
        }
        List<PatientWithIdDTO> patientWithIdDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            patientWithIdDTOS.add(new PatientWithIdDTO(patient));
        }
        return patientWithIdDTOS;
    }
	
	@Override
    public Patient changePassword(String newPassword, Patient user) {
		//da li treba neka provera ili odobrenje da mu se zada kod menjanja lozinke??
        user.setPassword(newPassword);
        return patientRepo.save(user);
    }
	
	@Override
	public PatientDTO editPersonalInformation(EditPatientDTO editPatientDTO) {
		Patient patient = getLoginPatient();
		
		if(patient.getId() != editPatientDTO.getId()) {
			return null;
		}
		
		patient.setFirstName(editPatientDTO.getFirstName());
		patient.setLastName(editPatientDTO.getLastName());
		patient.setAddress(editPatientDTO.getAddress());
		patient.setCity(editPatientDTO.getCity());
		patient.setPhoneNumber(editPatientDTO.getPhoneNumber());
		
		return new PatientDTO(patientRepo.save(patient));
		
	}
	
	@Override
	public Patient addAlergie(String drugName) {
		Patient patient = getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		List<Drug> drugs = drugRepo.findAll();
		
		for(Drug d : drugs) {
			if(d.getName().toLowerCase().contains(drugName.toLowerCase())) {
				patient.getAlergies().add(d);			
			}
		}
		
		patientRepo.save(patient);
		
		return patient;
		
	}
	
	/*
	 * @Override public List<DrugPricePharmacyNameAddressRatingDTO> getAll
	 */
	
	//kada su pregledi zavrseni 3.9
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice() {
		Patient patient = getLoginPatient();
		
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
				if(p.getExamination().getStatus().equals(ExaminationStatus.DONE)) {
					patientExaminations.add(p);
				}
			}
		}
		
			//pretvaranje rezultata u dto modele
		for(ExaminationPrice e : patientExaminations) {
			ExaminationPriceDTO dto = new ExaminationPriceDTO();
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su pregledi zavrseni
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByDate() {
		Patient patient = getLoginPatient();
		
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
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su pregledi zakazani
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByPriceBooked() {
		Patient patient = getLoginPatient();
		
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
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//kada su pregledi zakazani
	@Override
	public List<ExaminationPriceDTO> getAllExaminationPricesSortedByDateBooked() {
		Patient patient = getLoginPatient();
		
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
				if(p.getExamination().getStatus().equals(ExaminationStatus.BOOKED)) {
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
			dto.setExaminationName(e.getExamination().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getExamination().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getExamination().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	//3.9 za Savetovanja -------------------------------------------------------------------
	@Override
	public List<ConsultationPriceDTO> getAllConsultationPricesSortedByPrice() {
		Patient patient = getLoginPatient();
		
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
		Patient patient = getLoginPatient();
		
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
		Patient patient = getLoginPatient();
		
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
		Patient patient = getLoginPatient();
		
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
			dto.setConsultationName(e.getConsultation().getName());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setPrice(e.getPrice());
			dto.setStartDateTime(e.getConsultation().getInterval().getStartDateTime());
			dto.setEndDateTime(e.getConsultation().getInterval().getEndDateTime());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	//3.9 za rezervacije
	@Override
	public List<ReservationDTO> getAllReservations() {
		Patient patient = getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<Reservation> reservations = reservationRepo.findAll();
		List<Reservation> patientReservations = new ArrayList<>();
		List<ReservationDTO> dtos = new ArrayList<>();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(Reservation r : reservations) {
			if(r.getPatient().getId() == patient.getId()) {
				System.out.println(r.getStatus());
				if(r.getStatus().equals(ReservationStatus.ACTIVE)) {
					patientReservations.add(r);
				}
			}
		}
		
			//pretvaranje rezultata u dto modele
		for(Reservation e : patientReservations) {
			ReservationDTO dto = new ReservationDTO();
			dto.setDrugName(e.getDrug().getName());
			dto.setDrugCode(e.getDrug().getCode());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setGeneratedKey(e.getGeneratedKey());

			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	//Akcije i promocije
	@Override
	public List<PromotionDTO> getAllPromotions() {
		Patient patient = getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<Promotion> promotions = promotionRepo.findAll();
		List<Promotion> patientPromotions = new ArrayList<>();
		List<PromotionDTO> dtos = new ArrayList<>();
	
		
			//pretvaranje rezultata u dto modele
		for(Promotion p : promotions) {
			PromotionDTO dto = new PromotionDTO();
			dto.setContent(p.getContent());
			dto.setPharmacyName(p.getPharmacy().getName());
			dto.setStartDate(p.getPeriod().getStartDateTime().toString());
			dto.setEndDate(p.getPeriod().getEndDateTime().toString());

			dtos.add(dto);
		}
		
		return dtos;
	}
	
	//3.13 zakazivanje rezervacije pregleda
	@Override
	public ExaminationPriceDermatologistDTO makeExaminationReservation(Long examinationId) {
		Patient patient = getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		Examination e = examinationRepo.findOneById(examinationId);
		
		ExaminationPrice examination = examinationPriceRepo.findByExaminationId(examinationId);
		//System.out.println("1");
		if(examination == null) {
			return null;
		}
		
		
		
		if(examination.getExamination().getStatus() == ExaminationStatus.PREDEF_BOOKED) {
			//System.out.println("2");
			examination.getExamination().setStatus(ExaminationStatus.BOOKED);
			examination.getExamination().setPatient(patient);
			examinationPriceRepo.save(examination);
			//examinationRepo.save(examination.getExamination());
		}
		//System.out.println("3");
		ExaminationPriceDermatologistDTO dto = new ExaminationPriceDermatologistDTO();
		
		dto.setDermatologistName(examination.getExamination().getDermatologist().getFirstName());
		dto.setDermatologistRating(examination.getExamination().getDermatologist().getRating());
		dto.setPrice(examination.getPrice());
		dto.setExaminationName(examination.getExamination().getName());
		dto.setStartDateTime(examination.getExamination().getInterval().getStartDateTime());
		dto.setEndDateTime(examination.getExamination().getInterval().getEndDateTime());
		
		//salje se email na mejl pacijenta
		composeAndSendEmail(patient.getUsername(), dto.getExaminationName(), dto.getDermatologistName());
		
		return dto;
		
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
	
}
