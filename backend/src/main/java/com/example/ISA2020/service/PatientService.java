package com.example.ISA2020.service;

import java.util.List;

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
import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.dto.ReservationDTO;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.entity.users.Patient;

public interface PatientService {
	
	PatientDTO findById(Long id);
	
	Patient findByUsername(String username);
	
	List<Patient> getAllPatients();
	
	Patient getLoginPatient();
	
	Patient changePassword(String newPassword, Patient user);
	
	PatientDTO editPersonalInformation(EditPatientDTO editPatientDTO);
	
	//Patient addAlergie(String drugName);

	void save(Patient patient);

	PatientDTO addAlergie(Long drugId);

}
