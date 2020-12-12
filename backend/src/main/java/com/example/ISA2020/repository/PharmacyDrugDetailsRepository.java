package com.example.ISA2020.repository;

import com.example.ISA2020.entity.PharmacyDrugDetails;
import com.example.ISA2020.entity.PharmacyDrugKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyDrugDetailsRepository extends JpaRepository<PharmacyDrugDetails, PharmacyDrugKey>{
	
}
