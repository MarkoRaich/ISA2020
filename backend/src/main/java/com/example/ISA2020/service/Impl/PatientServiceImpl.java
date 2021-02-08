package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.DrugQuantityDTO;
import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.dto.GradeDermPharmDTO;
import com.example.ISA2020.dto.GradeDrugDTO;
import com.example.ISA2020.dto.GradePharmacyDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.dto.PatientWithIdDTO;
import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.dto.ReservationDTO;
import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.ExaminationPrice;
import com.example.ISA2020.entity.Grade;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.EntityStatus;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.repository.ConsultationPriceRepository;
import com.example.ISA2020.repository.ConsultationRepository;
import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.DrugPriceRepository;
import com.example.ISA2020.repository.DrugQuantityRepository;
import com.example.ISA2020.repository.DrugRepository;
import com.example.ISA2020.repository.ExaminationPriceRepository;
import com.example.ISA2020.repository.ExaminationRepository;
import com.example.ISA2020.repository.GradeRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.repository.PharmacistRepository;
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
	private DrugQuantityRepository drugQuantityRepo;
	
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
    
    @Autowired
    private ConsultationRepository consultationRepo;
    
    @Autowired
    private GradeRepository gradeRepo;
    
    @Autowired
    private PharmacistRepository pharmacistRepo;
    
    @Autowired
    private DermatologistRepository dermatologistRepo;
    

	
	
	// -------------------------------------------------------------------------
	
	

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
			String idString = e.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setDrugName(e.getDrug().getName());
			dto.setDrugCode(e.getDrug().getCode());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setGeneratedKey(e.getGeneratedKey());
			dto.setQuantity(e.getQuantity());

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
			String idString = p.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
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
		
		String idString = examination.getId().toString();
		Long id = Long.parseLong(idString);
		dto.setId(id);
		
		dto.setDermatologistName(examination.getExamination().getDermatologist().getFirstName());
		dto.setDermatologistRating(examination.getExamination().getDermatologist().getRating());
		dto.setPrice(examination.getPrice());
		dto.setExaminationName(examination.getExamination().getName());
		dto.setStartDateTime(examination.getExamination().getInterval().getStartDateTime());
		dto.setEndDateTime(examination.getExamination().getInterval().getEndDateTime());
		
		//salje se email na mejl pacijenta
		composeAndSendEmail(/*patient.getUsername()*/"", dto.getExaminationName(), dto.getDermatologistName());
		
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
    
    //3.15 Otkazivanje pregleda ----------------------------------------------------------
	@Override
	public ExaminationPriceDermatologistDTO cancelExaminationReservation(Long examinationId) {
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
		
		
		
		if(examination.getExamination().getStatus() == ExaminationStatus.BOOKED) {
			//if( )   PROVERA 24H!!!!
 			//System.out.println("2");
			examination.getExamination().setStatus(ExaminationStatus.CANCELED); //CANCELED???? PREDEF_BOOKED???
			examination.getExamination().setPatient(null);
			examinationPriceRepo.save(examination);
			//examinationRepo.save(examination.getExamination());
		}
		//System.out.println("3");
		ExaminationPriceDermatologistDTO dto = new ExaminationPriceDermatologistDTO();
		
		String idString = examination.getId().toString();
		Long id = Long.parseLong(idString);
		dto.setId(id);
		
		dto.setDermatologistName(examination.getExamination().getDermatologist().getFirstName());
		dto.setDermatologistRating(examination.getExamination().getDermatologist().getRating());
		dto.setPrice(examination.getPrice());
		dto.setExaminationName(examination.getExamination().getName());
		dto.setStartDateTime(examination.getExamination().getInterval().getStartDateTime());
		dto.setEndDateTime(examination.getExamination().getInterval().getEndDateTime());
		
		
		//salje se email na mejl pacijenta
		String subject = "Potvrda o otkazivanju pregleda";
        StringBuilder sb = new StringBuilder();
        sb.append("Postovani, otkazali ste pregled: ");
        sb.append(dto.getExaminationName());
        sb.append(" kod dermatologa: ");
        sb.append(dto.getDermatologistName());
        sb.append(System.lineSeparator());

        String text = sb.toString();

        
        emailNotificationService.sendEmail(/*patient.getUsername()*/ "", subject, text);
		
		return dto;
		
	}
	
	//3.16
	@Override
	public ConsultationPriceAddressDTO makeConsultationReservation(Long pharmacistId, Long pharmacyId) {

		Patient patient = getLoginPatient();
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

		Patient patient = getLoginPatient();
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
	
	
	//3.19 -------------------------------------------------------------------------------------------
	@Override
	public ReservationDTO makeDrugReservation(Long pharmacyId, Long drugId, int quantity) { //treba jos i krajnji datum do preuzimanja da se posalje!!!!!!
		
		Patient patient = getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		DrugQuantity drugQuantity = drugQuantityRepo.findByPharmacyIdAndDrugId(pharmacyId, drugId); //dodaj potvrdu preko maila
		
		if(drugQuantity == null) {
			return null;
		}
		
		if(drugQuantity.getQuantity() < quantity) {
			System.out.println("nema dovoljno lekova u apoteci");
			return null;
		}
		
		Reservation reservation = new Reservation();
		reservation.setPatient(patient);
		reservation.setPharmacy(drugQuantity.getPharmacy());
		reservation.setDrug(drugQuantity.getDrug());
		reservation.setStatus(ReservationStatus.ACTIVE);
		reservation.setQuantity(quantity);
		reservation.setInterval(null);
		
		
		Integer oldQuantity = drugQuantity.getQuantity();
		Integer newQuantity = oldQuantity - quantity;
		
		drugQuantity.setQuantity(newQuantity);
		if(drugQuantity.getQuantity() == oldQuantity) {
			System.out.println("1");
			return null;
		}
		drugQuantity.setStatus(EntityStatus.ACTIVE);
		
		drugQuantityRepo.save(drugQuantity);
		reservationRepo.save(reservation);
		
		
		//salje se email na mejl pacijenta
		String subject = "Potvrda o rezervaciji leka";
        StringBuilder sb = new StringBuilder();
        sb.append("Postovani, rezervisali ste lek: ");
        sb.append(reservation.getDrug().getName());
        sb.append(" u kolicini: ");
        sb.append(reservation.getQuantity());
        sb.append(", u apoteci: ");
        sb.append(reservation.getPharmacy().getName());
        sb.append(" koja se nalazi na adresi: ");
        sb.append(reservation.getPharmacy().getAddress());
        sb.append(System.lineSeparator());
        
        sb.append("Kod za preuzimanje leka: ");
        sb.append(reservation.getGeneratedKey());
        sb.append(System.lineSeparator());
        
        String text = sb.toString();

        
        emailNotificationService.sendEmail(/*patient.getUsername()*/ "", subject, text);
        
        ReservationDTO dto = new ReservationDTO();
		String idString = reservation.getId().toString();
		Long id = Long.parseLong(idString);
		dto.setId(id);
        dto.setDrugName(reservation.getDrug().getName());
        dto.setDrugCode(reservation.getDrug().getCode());
        dto.setQuantity(reservation.getQuantity());
        dto.setPharmacyName(reservation.getPharmacy().getName());
        dto.setGeneratedKey(reservation.getGeneratedKey());
		
		return dto;
		
	}
	
	@Override
	public List<DrugQuantityDTO> getAllDrugQuantities() {
		Patient patient = getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		List<DrugQuantity> drugQuantities = drugQuantityRepo.findAll();
		List<DrugQuantityDTO> dtos = new ArrayList<>();
		
		for(DrugQuantity d : drugQuantities) {
			DrugQuantityDTO dto = new DrugQuantityDTO();
			dto.setDrugName(d.getDrug().getName());
			dto.setDrugCode(d.getDrug().getCode());
			dto.setPharmacyName(d.getPharmacy().getName());
			
			dtos.add(dto);
		}
		
		return dtos;
	}
	

	//3.20  Otkazivanje rezervacije leka -------------------------------------------------------------
	@Override
	public ReservationDTO cancelDrugReservation(Long reservationId) {  //treba dodati proveru od 24h i penale
		
		Patient patient = getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Set<Reservation> reservations = patient.getReservations();
		
		ReservationDTO dto = new ReservationDTO();
		String address = null;
		
		for(Reservation r : reservations) {
			if(r.getId() == reservationId) {
				Integer reservedQuantity = r.getQuantity();
				r.setStatus(ReservationStatus.CANCELED);
				DrugQuantity drugQuantity = drugQuantityRepo.findByPharmacyIdAndDrugId(r.getPharmacy().getId(), r.getDrug().getId());
				if(drugQuantity == null) {
					System.out.println("nije pronasao drugQuantity");
					return null;
				}
				Integer oldQuantity = drugQuantity.getQuantity();
				Integer newQuantity = reservedQuantity + oldQuantity;
				drugQuantity.setQuantity(newQuantity);
				
				drugQuantityRepo.save(drugQuantity);
				reservationRepo.save(r);
				
				String idString = r.getId().toString();
				Long id = Long.parseLong(idString);
				dto.setId(id);
				
		        dto.setDrugName(r.getDrug().getName());
		        dto.setDrugCode(r.getDrug().getCode());
		        dto.setQuantity(r.getQuantity());
		        dto.setPharmacyName(r.getPharmacy().getName());
		        dto.setGeneratedKey(r.getGeneratedKey());
		        address = r.getPharmacy().getAddress();
			}
		}
		
		
		//salje se email na mejl pacijenta
		String subject = "Potvrda o otkazivanju rezervacije leka";
        StringBuilder sb = new StringBuilder();
        sb.append("Postovani, otkazali ste rezervaciju za lek: ");
        sb.append(dto.getDrugName());
        sb.append(" u kolicini: ");
        sb.append(dto.getQuantity());
        sb.append(", u apoteci: ");
        sb.append(dto.getPharmacyName());
        sb.append(" koja se nalazi na adresi: ");
        sb.append(address);
        sb.append(System.lineSeparator());
        
        sb.append("Kod koji je sluzio za preuzimanje leka: ");
        sb.append(dto.getGeneratedKey());
        sb.append(System.lineSeparator());
        
        String text = sb.toString();

        
        emailNotificationService.sendEmail(/*patient.getUsername()*/ "", subject, text);

		
		return dto;
		
	}
	
	//Ocena za lek ------------------------------------------------------------------------------
	@Override
	public GradeDrugDTO setGradeForDrug(Long id, double grade) {
		
		Patient patient = getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Drug drug = drugRepo.findOneById(id);
		if(drug == null) {
			System.out.println("Drug nije pronadjen.");
			return null;
		}
		
		List<Grade> patientGrades = gradeRepo.findByPatientId(patient.getId());
		for(Grade g : patientGrades) {
			if(g.getDrug().getId() != null) {
				if(g.getDrug().getId() == drug.getId()) {
					System.out.println("Pacijent je vec ocenio taj lek.");
					return null;
				}
			}
		}
		
		List<Reservation> patientReservations = reservationRepo.findByPatientIdAndDrugId(patient.getId(), drug.getId());
		
		boolean status = false; //za proveru da li je preuzeo rezervaciju leka barem jednom!!!!!!!!!!!!!!! 
		for(Reservation r : patientReservations) {
			if(r.getStatus() == ReservationStatus.COMPLETED) {
				status = true;
			}
		}
		
		
		List<Grade> drugGrades = gradeRepo.findByDrugId(drug.getId());
		Integer numberOfGrades = drugGrades.size();
		Double numberOfGradesDouble = (double)numberOfGrades;
		
		if(status) {
			Grade drugGrade = new Grade();
			drugGrade.setPatient(patient);
			drugGrade.setGrade(grade);
			drugGrade.setDrug(drug);
			drugGrade.setDermatologist(null);
			drugGrade.setPharmacist(null);
			drugGrade.setPharmacy(null);
			
			//proverava koliko ima ocena za taj lek i postavlja novu prosecnu ocenu leka
			Double oldGrade = drug.getRating();
			if(oldGrade == null) {
				oldGrade = 0.0;
			}
			Double newGrade;
			Double sum;
			if(oldGrade == 0) {
				newGrade = grade;
				drug.setRating(newGrade);
				drugRepo.save(drug);
			} else {
				sum = oldGrade + grade;
				newGrade = sum/numberOfGradesDouble;
				drug.setRating(newGrade);
				drugRepo.save(drug);
			}

			gradeRepo.save(drugGrade);
			
			GradeDrugDTO dto = new GradeDrugDTO();
			dto.setId(drugGrade.getId());
			dto.setDrugName(drugGrade.getDrug().getName());
			dto.setDrugCode(drugGrade.getDrug().getCode());
			dto.setGrade(grade);
			
			
			return dto;
		}
		
		return null;

	}
	
	//Ocena za apoteku --------------------------------------------------
	@Override
	public GradePharmacyDTO setGradeForPharmacy(Long id, double grade) {
		
		Patient patient = getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		if(pharmacy == null) {
			System.out.println("Pharmacy nije pronadjen.");
			return null;
		}
		
		List<Grade> patientGrades = gradeRepo.findByPatientId(patient.getId());
		/*for(Grade g : patientGrades) {
			if(g.getPharmacy().getId() != null) {
				if(g.getPharmacy().getId() == pharmacy.getId()) {
					System.out.println("Pacijent je vec ocenio tu Apoteku.");
					return null;
				}
			}
		} */
		
		List<Reservation> patientReservations = reservationRepo.findByPatientIdAndPharmacyId(patient.getId(), pharmacy.getId());
		
		//za proveru da li je preuzeo rezervaciju leka barem jednom!
		boolean status = false; 	
		for(Reservation r : patientReservations) {
			if(r.getStatus() == ReservationStatus.COMPLETED) {
				status = true;
			}
		}
		
		//provera da li je imao bar jedan pregled u toj apoteci
		Set<Examination> patientExaminations = patient.getExaminations();  // MORAMO DODATI NEKO POLJE ZA EXAMINATION
		for(Examination e : patientExaminations) {
			if(e.getPharmacy().getId() != null) {
				if(e.getPharmacy().getId() == pharmacy.getId()) {
					status = true;
				}		
			}
		} 
		
		//provera da li je imao bar jedne konsultacije u toj apoteci
		Set<Consultation> patientConsultations = patient.getConsultations();
		for(Consultation c : patientConsultations) {
			if(c.getPharmacist().getId() != null) {
				if(c.getPharmacist().getPharmacy().getId() == pharmacy.getId()) {
					status = true;
				}
			}
		}
		
		
		
		List<Grade> pharmacyGrades = gradeRepo.findByPharmacyId(pharmacy.getId());
		Integer numberOfGrades = pharmacyGrades.size();
		Double numberOfGradesDouble = (double)numberOfGrades;
		
		if(status) {
			Grade pharmacyGrade = new Grade();
			pharmacyGrade.setPatient(patient);
			pharmacyGrade.setGrade(grade);
			pharmacyGrade.setDrug(null);
			pharmacyGrade.setDermatologist(null);
			pharmacyGrade.setPharmacist(null);
			pharmacyGrade.setPharmacy(pharmacy);
			
			//proverava koliko ima ocena za taj lek i postavlja novu prosecnu ocenu leka
			Double oldGrade = pharmacy.getRating();
			if(oldGrade == null) {
				oldGrade = 0.0;
			}
			Double newGrade;
			Double sum;
			if(oldGrade == 0) {
				newGrade = grade;
				pharmacy.setRating(newGrade);
				pharmacyRepo.save(pharmacy);
			} else {
				sum = oldGrade + grade;
				newGrade = sum/numberOfGradesDouble;
				pharmacy.setRating(newGrade);
				pharmacyRepo.save(pharmacy);
			}

			gradeRepo.save(pharmacyGrade);
			
			GradePharmacyDTO dto = new GradePharmacyDTO();
			dto.setId(pharmacyGrade.getId());
			dto.setPharmacyName(pharmacy.getName());
			dto.setPharmacyAddress(pharmacy.getAddress());
			dto.setGrade(grade);
			
			
			return dto;
		}
		
		return null;

	}
	
	
	//Ocena za dermatologa --------------------------------------------------
	@Override
	public GradeDermPharmDTO setGradeForDermatologist(Long id, double grade) {
		
		Patient patient = getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Dermatologist dermatologist = dermatologistRepo.findOneById(id);
		if(dermatologist == null) {
			System.out.println("Dermatologist nije pronadjen.");
			return null;
		}
		
		List<Grade> patientGrades = gradeRepo.findByPatientId(patient.getId());
		/*for(Grade g : patientGrades) {
			if(g.getDermatologist().getId() != null) {
				if(g.getDermatologist().getId() == dermatologist.getId()) {
					System.out.println("Pacijent je vec ocenio tog dermatologa.");
					return null;
				}
			}
		} */
		
		boolean status = false; 
		//provera da li je imao bar jedan pregled kod tog dermatologa
		Set<Examination> patientExaminations = patient.getExaminations();  // MORAMO DODATI NEKO POLJE ZA EXAMINATION     -> DONE
		for(Examination e : patientExaminations) {
			if(e.getDermatologist().getId() != null) {
				if(e.getDermatologist().getId() == dermatologist.getId()) {
					status = true;
				}	
			}
		}
		

		List<Grade> dermatologistGrades = gradeRepo.findByDermatologistId(dermatologist.getId());
		Integer numberOfGrades = dermatologistGrades.size();
		Double numberOfGradesDouble = (double)numberOfGrades;
		
		if(status) {
			Grade dermatologistGrade = new Grade();
			dermatologistGrade.setPatient(patient);
			dermatologistGrade.setGrade(grade);
			dermatologistGrade.setDrug(null);
			dermatologistGrade.setDermatologist(dermatologist);
			dermatologistGrade.setPharmacist(null);
			dermatologistGrade.setPharmacy(null);
			
			//proverava koliko ima ocena za taj lek i postavlja novu prosecnu ocenu leka
			Double oldGrade = dermatologist.getRating();
			if(oldGrade == null) {
				oldGrade = 0.0;
			}
			Double newGrade;
			Double sum;
			if(oldGrade == 0) {
				newGrade = grade;
				dermatologist.setRating(newGrade);
				dermatologistRepo.save(dermatologist);
			} else {
				sum = oldGrade + grade;
				newGrade = sum/numberOfGradesDouble;
				dermatologist.setRating(newGrade);
				dermatologistRepo.save(dermatologist);
			}

			gradeRepo.save(dermatologistGrade);
			
			GradeDermPharmDTO dto = new GradeDermPharmDTO();
			dto.setId(dermatologistGrade.getId());
			dto.setFirstName(dermatologist.getFirstName());
			dto.setLastName(dermatologist.getLastName());
			dto.setGrade(grade);
			
			
			return dto;
		}
		
		return null;

	}
	
	
	
	//Ocena za farmaceuta --------------------------------------------------
	@Override
	public GradeDermPharmDTO setGradeForPharmacist(Long id, double grade) {
		
		Patient patient = getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Pharmacist pharmacist = pharmacistRepo.findOneById(id);
		if(pharmacist == null) {
			System.out.println("Pharmacist nije pronadjen.");
			return null;
		}
		
		List<Grade> patientGrades = gradeRepo.findByPatientId(patient.getId());
		/*for(Grade g : patientGrades) {
			if(g.getPharmacist().getId() != null) {
				if(g.getPharmacist().getId() == pharmacist.getId()) {
					System.out.println("Pacijent je vec ocenio tog faramceuta.");
					return null;
				}
			}
		} */ // NE RAZUMEM ZASTO PUCA KOD OVIH PROVERA
		
		boolean status = false; 
		//provera da li je imao bar jedan pregled kod tog dermatologa
		Set<Consultation> patientConsultations = patient.getConsultations(); 
		for(Consultation e : patientConsultations) {
			if(e.getPharmacist().getId() != null) {
				if(e.getPharmacist().getId() == pharmacist.getId()) {
					status = true;
				}
			}
		}
		

		List<Grade> pharmacistGrades = gradeRepo.findByPharmacistId(pharmacist.getId());
		Integer numberOfGrades = pharmacistGrades.size();
		Double numberOfGradesDouble = (double)numberOfGrades;
		
		if(status) {
			Grade pharmacistGrade = new Grade();
			pharmacistGrade.setPatient(patient);
			pharmacistGrade.setGrade(grade);
			pharmacistGrade.setDrug(null);
			pharmacistGrade.setDermatologist(null);
			pharmacistGrade.setPharmacist(pharmacist);
			pharmacistGrade.setPharmacy(null);
			
			//proverava koliko ima ocena za taj lek i postavlja novu prosecnu ocenu leka
			Double oldGrade = pharmacist.getRating();
			if(oldGrade == null) {
				oldGrade = 0.0;
			}
			Double newGrade;
			Double sum;
			if(oldGrade == 0) {
				newGrade = grade;
				pharmacist.setRating(newGrade);
				pharmacistRepo.save(pharmacist);
			} else {
				sum = oldGrade + grade;
				newGrade = sum/numberOfGradesDouble;
				pharmacist.setRating(newGrade);
				pharmacistRepo.save(pharmacist);
			}

			gradeRepo.save(pharmacistGrade);
			
			GradeDermPharmDTO dto = new GradeDermPharmDTO();
			dto.setId(pharmacistGrade.getId());
			dto.setFirstName(pharmacist.getFirstName());
			dto.setLastName(pharmacist.getLastName());
			dto.setGrade(grade);
			
			
			return dto;
		}
		
		return null;

	}
	
	
}
