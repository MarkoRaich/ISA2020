package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.EditPatientDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.dto.PromotionDTO;
import com.example.ISA2020.dto.ReservationDTO;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.service.ExaminationPriceService;
import com.example.ISA2020.service.PatientService;
import com.example.ISA2020.service.PharmacyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private ExaminationPriceService examinationPriceService;
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Patient>> getAll() {
		List<Patient> patients = patientService.getAllPatients();
		if(patients == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Patient> getOne(@PathVariable Long id) {
		Patient patient = patientService.findById(id);
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<PatientDTO> editPersonalInformation(@Valid @RequestBody EditPatientDTO editPatientDTO) {
		PatientDTO patientDTO = patientService.editPersonalInformation(editPatientDTO);
		if(patientDTO == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/addAlergie")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<Patient> addAlergie(@RequestParam("drugName") String drugName) {
		Patient patient = patientService.addAlergie(drugName);
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(patient, HttpStatus.CREATED);
	}
	
	
	// 3.9 --------------------------------------------------------------------------------------------------
	@GetMapping("/getAllPharmaciesSortedByAddress")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesByAddress() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyAddress();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllPharmaciesSortedByName")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesByName() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyName();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllPharmaciesSortedByRating")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesByRating() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyRating();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getAllExaminationsSortedByPrice")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByPrice() {
		List<ExaminationPriceDTO> dtos = patientService.getAllExaminationPricesSortedByPrice();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllExaminationsSortedByDate")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByDate() {
		List<ExaminationPriceDTO> dtos = patientService.getAllExaminationPricesSortedByDate();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByPrice")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByPrice() {
		List<ConsultationPriceDTO> dtos = patientService.getAllConsultationPricesSortedByPrice();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByDate")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByDate() {
		List<ConsultationPriceDTO> dtos = patientService.getAllConsultationPricesSortedByDate();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	//zakazani pregledi i savetovanja
	@GetMapping("/getAllExaminationsSortedByPriceBooked")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByPriceBooked() {
		List<ExaminationPriceDTO> dtos = patientService.getAllExaminationPricesSortedByPriceBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllExaminationsSortedByDateBooked")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByDateBooked() {
		List<ExaminationPriceDTO> dtos = patientService.getAllExaminationPricesSortedByDateBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByPriceBooked")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByPriceBooked() {
		List<ConsultationPriceDTO> dtos = patientService.getAllConsultationPricesSortedByPriceBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByDateBooked")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByDateBooked() {
		List<ConsultationPriceDTO> dtos = patientService.getAllConsultationPricesSortedByDateBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	//rezervacije
	@GetMapping("/getAllReservations")
	public ResponseEntity<List<ReservationDTO>> getAllReservations() {
		List<ReservationDTO> dtos = patientService.getAllReservations();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ReservationDTO>>(dtos, HttpStatus.OK);
	}
	
	//akcije i promocije
	@GetMapping("/getAllPromotions")
	public ResponseEntity<List<PromotionDTO>> getAllPromotions() {
		List<PromotionDTO> dtos = patientService.getAllPromotions();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PromotionDTO>>(dtos, HttpStatus.OK);
	}
	
	//3.13 -----------------------------------------------------------
	@PutMapping("/makeExaminationReservation/{id}")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<ExaminationPriceDermatologistDTO> makeExaminationReservation(@PathVariable Long id) {
		ExaminationPriceDermatologistDTO dto = patientService.makeExaminationReservation(id);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

}
