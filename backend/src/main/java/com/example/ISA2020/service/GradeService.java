package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.GradeDermPharmDTO;
import com.example.ISA2020.dto.GradeDrugDTO;
import com.example.ISA2020.dto.GradePharmacyDTO;
import com.example.ISA2020.entity.Grade;

public interface GradeService {
	
	Grade findById(Long id);
	
	List<Grade> getAllConsultations();
	
	//Ocenjivanje lekova, apoteka,farmaceuta, dermatologa  3.41 -----------------------------------------------
	GradeDrugDTO setGradeForDrug(Long id, double grade);
	
	GradePharmacyDTO setGradeForPharmacy(Long id, double grade);
	
	GradeDermPharmDTO setGradeForDermatologist(Long id, double grade);
	
	GradeDermPharmDTO setGradeForPharmacist(Long id, double grade);
}
