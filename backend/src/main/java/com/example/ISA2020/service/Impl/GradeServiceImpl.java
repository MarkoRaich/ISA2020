package com.example.ISA2020.service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.GradeDermPharmDTO;
import com.example.ISA2020.dto.GradeDrugDTO;
import com.example.ISA2020.dto.GradePharmacyDTO;
import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.Grade;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.DrugRepository;
import com.example.ISA2020.repository.GradeRepository;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.repository.ReservationRepository;
import com.example.ISA2020.service.GradeService;
import com.example.ISA2020.service.PatientService;

@Service
public class GradeServiceImpl implements GradeService {
	
	@Autowired
	private GradeRepository gradeRepo;
	
	
	@Autowired
	private DrugRepository drugRepo;
	
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	
	@Autowired
	private PharmacistRepository pharmacistRepo;
	
	@Autowired
	private DermatologistRepository dermatologistRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private PatientService patientService;
	
	
	
	@Override
	public Grade findById(Long id) {
		return gradeRepo.findOneById(id);
	}

	@Override
	public List<Grade> getAllConsultations() {
		return gradeRepo.findAll();
	}
	
	
	//Ocena za lek ------------------------------------------------------------------------------
	@Override
	public GradeDrugDTO setGradeForDrug(Long id, double grade) {
		
		Patient patient = patientService.getLoginPatient();
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
			if(g.getDrug() != null) {
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
		

		if(numberOfGradesDouble == 0.0) {
			numberOfGradesDouble = 2.0;
		}
		
		Double newGrade = 0.0;
		Double sum = 0.0;
		
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

			if(oldGrade == 0) {
				newGrade = grade;
				drug.setRating(newGrade);
				drugRepo.save(drug);
			} else {
				System.out.println("else");
				sum += oldGrade;
				sum += grade;
				System.out.println("sum = " + sum);
				newGrade = sum/numberOfGradesDouble;
				System.out.println("newGrade = " + newGrade);
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
		
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Pharmacy pharmacy = pharmacyRepo.findOneById(id);
		if(pharmacy == null) {
			System.out.println("Pharmacy nije pronadjen.");
			return null;
		}
		
		List<Grade> patientGrades = gradeRepo.findByPatientId(patient.getId());
		for(Grade g : patientGrades) {
			if(g.getPharmacy() != null) {
				if(g.getPharmacy().getId() == pharmacy.getId()) {
					System.out.println("Pacijent je vec ocenio tu Apoteku.");
					return null;
				}
			}
		} 
		
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
			if(e.getPharmacy() != null) {
				if(e.getPharmacy().getId() == pharmacy.getId()) {
					status = true;
				}		
			}
		} 
		
		//provera da li je imao bar jedne konsultacije u toj apoteci
		Set<Consultation> patientConsultations = patient.getConsultations();
		for(Consultation c : patientConsultations) {
			if(c.getPharmacist() != null) {
				if(c.getPharmacist().getPharmacy().getId() == pharmacy.getId()) {
					status = true;
				}
			}
		}
		
		
		
		List<Grade> pharmacyGrades = gradeRepo.findByPharmacyId(pharmacy.getId());
		Integer numberOfGrades = pharmacyGrades.size();
		Double numberOfGradesDouble = (double)numberOfGrades;


		if(numberOfGradesDouble == 0.0) {
			numberOfGradesDouble = 2.0;
		}
		
		Double newGrade = 0.0;
		Double sum = 0.0;
		
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

			if(oldGrade == 0) {
				newGrade = grade;
				pharmacy.setRating(newGrade);
				pharmacyRepo.save(pharmacy);
			} else {
				System.out.println("else");
				sum += oldGrade;
				sum += grade;
				System.out.println("sum = " + sum);
				newGrade = sum/numberOfGradesDouble;
				System.out.println("newGrade = " + newGrade);
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
		
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Dermatologist dermatologist = dermatologistRepo.findOneById(id);
		if(dermatologist == null) {
			System.out.println("Dermatologist nije pronadjen.");
			return null;
		}
		
		List<Grade> patientGrades = gradeRepo.findByPatientId(patient.getId());
		for(Grade g : patientGrades) {
			if(g.getDermatologist() != null) {
				if(g.getDermatologist().getId() == dermatologist.getId()) {
					System.out.println("Pacijent je vec ocenio tog dermatologa.");
					return null;
				}
			}
		} 
		
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

		if(numberOfGradesDouble == 0.0) {
			numberOfGradesDouble = 2.0;
		}
		
		if(status) {
			
			//proverava koliko ima ocena za taj lek i postavlja novu prosecnu ocenu leka
			Double oldGrade = dermatologist.getRating();
			if(oldGrade == null) {
				oldGrade = 0.0;
			}
			Double newGrade = 0.0;
			Double sum = 0.0;
			if(oldGrade == 0) {
				newGrade = grade;
				dermatologist.setRating(newGrade);
				dermatologistRepo.save(dermatologist);
			} else {
				System.out.println("else");
				sum += oldGrade;
				sum += grade;
				System.out.println("sum = " + sum);
				newGrade = sum/numberOfGradesDouble;
				System.out.println("newGrade = " + newGrade);
				dermatologist.setRating(newGrade);
				dermatologistRepo.save(dermatologist);
			}
			
			Grade dermatologistGrade = new Grade();
			dermatologistGrade.setPatient(patient);
			dermatologistGrade.setGrade(grade);
			dermatologistGrade.setDrug(null);
			dermatologistGrade.setDermatologist(dermatologist);
			dermatologistGrade.setPharmacist(null);
			dermatologistGrade.setPharmacy(null);

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
		
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Pharmacist pharmacist = pharmacistRepo.findOneById(id);
		if(pharmacist == null) {
			System.out.println("Pharmacist nije pronadjen.");
			return null;
		}
		
		List<Grade> patientGrades = gradeRepo.findByPatientId(patient.getId());
		for(Grade g : patientGrades) {
			if(g.getPharmacist() != null) {
				if(g.getPharmacist().getId() == pharmacist.getId()) {
					System.out.println("Pacijent je vec ocenio tog faramceuta.");
					return null;
				}
			}
		}  // NE RAZUMEM ZASTO PUCA KOD OVIH PROVERA
		
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
		if(numberOfGradesDouble == 0.0) {
			numberOfGradesDouble = 2.0;
		}
		
		if(status) {
			
			//proverava koliko ima ocena za taj lek i postavlja novu prosecnu ocenu leka
			Double oldGrade = pharmacist.getRating();
			System.out.println(pharmacist.getRating());
			if(oldGrade == null) {
				oldGrade = 0.0;
			}
			Double newGrade = 0.0;
			Double sum = 0.0;
			if(oldGrade == 0.0) {
				System.out.println(pharmacist.getRating() + " je 0!");
				newGrade = grade;
				pharmacist.setRating(newGrade);
				pharmacistRepo.save(pharmacist);
			} else {
				System.out.println("else");
				sum += oldGrade;
				sum += grade;
				System.out.println("sum = " + sum);
				newGrade = sum/numberOfGradesDouble;
				System.out.println("newGrade = " + newGrade);
				pharmacist.setRating(newGrade);
				pharmacistRepo.save(pharmacist);
			}
			
			Grade pharmacistGrade = new Grade();
			pharmacistGrade.setPatient(patient);
			pharmacistGrade.setGrade(grade);
			pharmacistGrade.setDrug(null);
			pharmacistGrade.setDermatologist(null);
			pharmacistGrade.setPharmacist(pharmacist);
			pharmacistGrade.setPharmacy(null);

			gradeRepo.save(pharmacistGrade);
			
			GradeDermPharmDTO dto = new GradeDermPharmDTO();
			dto.setId(pharmacistGrade.getId());
			dto.setFirstName(pharmacist.getFirstName());
			dto.setLastName(pharmacist.getLastName());
			dto.setGrade(newGrade);
			
			
			return dto;
		}
		
		return null;
	}

}
