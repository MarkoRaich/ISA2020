package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.dto.ReservationDTO;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.service.Impl.PharmacistSimpleDTO;

public interface PatientService {
	
	Patient findById(Long id);
	
	Patient findByUsername(String username);
	
	List<Patient> getAllPatients();
	
	Patient getLoginPatient();
	
	Patient changePassword(String newPassword, Patient user);
	
	PatientDTO editPersonalInformation(EditPatientDTO editPatientDTO);
	
	Patient addAlergie(String drugName);
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByPrice();
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByDate();
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByPrice();
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByDate();
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByPriceBooked();
	
	List<ExaminationPriceDTO> getAllExaminationPricesSortedByDateBooked();
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByPriceBooked();
	
	List<ConsultationPriceDTO> getAllConsultationPricesSortedByDateBooked();
	
	List<ReservationDTO> getAllReservations();
	
	List<PromotionDTO> getAllPromotions();
	
	ExaminationPriceDermatologistDTO makeExaminationReservation(Long examinationId);
	
	ExaminationPriceDermatologistDTO cancelExaminationReservation(Long examinationId);
	
	ConsultationPriceAddressDTO makeConsultationReservation(Long pharmacistId, Long pharmacyId);
	
	ConsultationPriceAddressDTO cancelConsultationReservation(Long pharmacistId, Long pharmacyId);
}
