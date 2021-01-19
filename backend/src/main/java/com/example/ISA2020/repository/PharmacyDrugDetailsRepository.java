package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.DrugQuantityInPharmacy;
import com.example.ISA2020.entity.PharmacyDrugKey;

public interface PharmacyDrugDetailsRepository extends JpaRepository<DrugQuantityInPharmacy, PharmacyDrugKey>{
	
	DrugQuantityInPharmacy findOneById(PharmacyDrugKey id);
	
	List<DrugQuantityInPharmacy> findAll();
	
//	PharmacyDrugDetails save(Pha)
}
