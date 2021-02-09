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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
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
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.service.ConsultationPriceService;
import com.example.ISA2020.service.ExaminationPriceService;
import com.example.ISA2020.service.GradeService;
import com.example.ISA2020.service.PatientService;
import com.example.ISA2020.service.PharmacyService;
import com.example.ISA2020.service.PromotionService;
import com.example.ISA2020.service.ReservationService;

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
	
	@Autowired
	private ConsultationPriceService consultationPriceService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private PromotionService promotionService;
	

	
	
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
	
	@PutMapping("/editInfo")
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
		List<ExaminationPriceDTO> dtos = examinationPriceService.getAllExaminationPricesSortedByPrice();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllExaminationsSortedByDate")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByDate() {
		List<ExaminationPriceDTO> dtos = examinationPriceService.getAllExaminationPricesSortedByDate();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByPrice")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByPrice() {
		List<ConsultationPriceDTO> dtos = consultationPriceService.getAllConsultationPricesSortedByPrice();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByDate")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByDate() {
		List<ConsultationPriceDTO> dtos = consultationPriceService.getAllConsultationPricesSortedByDate();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	//zakazani pregledi i savetovanja
	@GetMapping("/getAllExaminationsSortedByPriceBooked")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByPriceBooked() {
		List<ExaminationPriceDTO> dtos = examinationPriceService.getAllExaminationPricesSortedByPriceBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllExaminationsSortedByDateBooked")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByDateBooked() {
		List<ExaminationPriceDTO> dtos = examinationPriceService.getAllExaminationPricesSortedByDateBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByPriceBooked")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByPriceBooked() {
		List<ConsultationPriceDTO> dtos = consultationPriceService.getAllConsultationPricesSortedByPriceBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllConsultationsSortedByDateBooked")
	public ResponseEntity<List<ConsultationPriceDTO>> getAllConsultationsByDateBooked() {
		List<ConsultationPriceDTO> dtos = consultationPriceService.getAllConsultationPricesSortedByDateBooked();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	//rezervacije
	@GetMapping("/getAllReservations")
	public ResponseEntity<List<ReservationDTO>> getAllReservations() {
		List<ReservationDTO> dtos = reservationService.getAllReservationsActive();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ReservationDTO>>(dtos, HttpStatus.OK);
	}
	
	//akcije i promocije
	@GetMapping("/getAllPromotions")
	public ResponseEntity<List<PromotionDTO>> getAllPromotions() {
		List<PromotionDTO> dtos = promotionService.getAllPromotionsDTO();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PromotionDTO>>(dtos, HttpStatus.OK);
	}
	
	//3.13 -----------------------------------------------------------
	@PutMapping("/makeExaminationReservation/{id}")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<ExaminationPriceDermatologistDTO> makeExaminationReservation(@PathVariable Long id) {
		ExaminationPriceDermatologistDTO dto = examinationPriceService.makeExaminationReservation(id);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/cancelExaminationReservation/{id}")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<ExaminationPriceDermatologistDTO> cancelExaminationReservation(@PathVariable Long id) {
		ExaminationPriceDermatologistDTO dto = examinationPriceService.cancelExaminationReservation(id);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	//3.16 ------------------------
	@PutMapping("/makeConsultationReservation")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<ConsultationPriceAddressDTO> makeExaminationReservation(@RequestParam("pharmacistId") Long pharmacistId, @RequestParam("pharmacyId") Long pharmacyId) {
		ConsultationPriceAddressDTO dto = consultationPriceService.makeConsultationReservation(pharmacistId, pharmacyId);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	//3.18
	@PutMapping("/cancelConsultationReservation")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<ConsultationPriceAddressDTO> cancelExaminationReservation(@RequestParam("pharmacistId") Long pharmacistId, @RequestParam("pharmacyId") Long pharmacyId) {
		ConsultationPriceAddressDTO dto = consultationPriceService.cancelConsultationReservation(pharmacistId, pharmacyId);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	
	
	//3.19 -------------------------------------------------------
	@PutMapping("/makeDrugReservation") //dodaj interval za rezervaciju i za otkazivanje rezervacije
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<ReservationDTO> makeDrugReservation(@RequestParam("pharmacyId") Long pharmacyId, @RequestParam("drugId") Long drugId, 
															@RequestParam("quantity") int quantity) {
		ReservationDTO dto = reservationService.makeDrugReservation(pharmacyId, drugId, quantity);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAllDrugQuantities")
	public ResponseEntity<List<DrugQuantityDTO>> getAllDrugQuantities() {
		List<DrugQuantityDTO> dtos = reservationService.getAllDrugQuantities();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugQuantityDTO>>(dtos, HttpStatus.OK);
	}
	
	//3.20
	@PutMapping("/cancelDrugReservation")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<ReservationDTO> cancelDrugReservation(@RequestParam("reservationId") Long reservationId) {
		ReservationDTO dto = reservationService.cancelDrugReservation(reservationId);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	
	//3.41 --------------------------------------------------------------------
	@PutMapping("/setDrugGrade")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<GradeDrugDTO> setDrugGrade(@RequestParam("drugId") Long drugId, @RequestParam("grade") double grade) {
		GradeDrugDTO dto = gradeService.setGradeForDrug(drugId, grade);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/setPharmacyGrade")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<GradePharmacyDTO> setPharmacyGrade(@RequestParam("pharmacyId") Long pharmacyId, @RequestParam("grade") double grade) {
		GradePharmacyDTO dto = gradeService.setGradeForPharmacy(pharmacyId, grade);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/setDermatologistGrade")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<GradeDermPharmDTO> setDermatologistGrade(@RequestParam("dermatologistId") Long dermatologistId, @RequestParam("grade") double grade) {
		GradeDermPharmDTO dto = gradeService.setGradeForDermatologist(dermatologistId, grade);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/setPharmacistGrade")
    //@PreAuthorize("hasRole('PATIENT')") //ROLE_PATIENT??
	public ResponseEntity<GradeDermPharmDTO> setPharmacistGrade(@RequestParam("pharmacistId") Long pharmacistId, @RequestParam("grade") double grade) {
		GradeDermPharmDTO dto = gradeService.setGradeForPharmacist(pharmacistId, grade);
		if(dto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	

}
