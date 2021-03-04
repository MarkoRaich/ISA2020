package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.dto.DrugPriceDTO;
import com.example.ISA2020.entity.DrugPrice;

public interface DrugPriceRepository extends JpaRepository<DrugPrice, Long>{
	
	DrugPrice findOneById(Long id);
	
	List<DrugPrice> findAll();
	
	DrugPrice findByPharmacyIdAndDrugId(Long pharmacyId, Long drugId);

	List<DrugPrice> findAllByPharmacyId(Long pharmId);
}
