package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.PharmDrugQuantityKey;
import com.example.ISA2020.enumeration.EntityStatus;

public interface DrugQuantityRepository extends JpaRepository<DrugQuantity, Long> {

	DrugQuantity findOneById(PharmDrugQuantityKey id);
	
	List<DrugQuantity> findAll();

	List<DrugQuantity> findByPharmacyId(Long pharmId);

	List<DrugQuantity> findByPharmacyIdAndStatusNot(Long pharmId, EntityStatus status);
	
	DrugQuantity findByPharmacyIdAndDrugId(Long pharmacyId, Long drugId);

	List<DrugQuantity> findByPharmacyIdAndStatusNotAndDrugNameContainsIgnoringCase(Long pharmId, EntityStatus status, String name);

	List<DrugQuantity> findByPharmacyIdAndStatus(Long pharmId, EntityStatus status);

	//DrugQuantity findById(Long id, Long id2);
}
