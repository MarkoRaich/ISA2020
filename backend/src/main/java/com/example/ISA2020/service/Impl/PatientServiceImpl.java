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
import com.example.ISA2020.dto.PharmacistSimpleDTO;
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
		
//		if(patient.getId() != editPatientDTO.getId()) {
//			System.out.println("1");
//			return null;
//		}
		
		System.out.println("2");
		patient.setFirstName(editPatientDTO.getFirstName());
		patient.setLastName(editPatientDTO.getLastName());
		patient.setAddress(editPatientDTO.getAddress());
		patient.setCity(editPatientDTO.getCity());
		patient.setPhoneNumber(editPatientDTO.getPhoneNumber());
		
		return new PatientDTO(patientRepo.save(patient));
		
	}
	
	@Override
	public Patient addAlergie(String drugName) { //promeni sta vraca na dto
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

}
