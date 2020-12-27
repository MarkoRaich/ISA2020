package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.PharmacyDrugDetails;
import com.example.ISA2020.entity.PharmacyDrugKey;

public interface PharmacyDrugDetailsRepository extends JpaRepository<PharmacyDrugDetails, PharmacyDrugKey>{
	
	PharmacyDrugDetails findOneById(PharmacyDrugKey id);
	
	List<PharmacyDrugDetails> findAll();
	
//	PharmacyDrugDetails save(Pha)
}
