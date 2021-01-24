package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.PharmacyDrugKey;

public interface PharmacyDrugDetailsRepository extends JpaRepository<DrugQuantity, PharmacyDrugKey>{
	
	DrugQuantity findOneById(PharmacyDrugKey id);
	
	List<DrugQuantity> findAll();
	
//	PharmacyDrugDetails save(Pha)
}
