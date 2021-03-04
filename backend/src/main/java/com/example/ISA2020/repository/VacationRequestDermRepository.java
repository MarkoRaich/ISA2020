package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.VacationRequestDerm;
import com.example.ISA2020.enumeration.VacationRequestStatus;

public interface VacationRequestDermRepository extends JpaRepository<VacationRequestDerm, Long>{

	VacationRequestDerm findOneById(Long id);
	
	List<VacationRequestDerm> findAll();

	List<VacationRequestDerm> findByDermatologistIdAndStatusNot(Long id, VacationRequestStatus status);

	List<VacationRequestDerm> findByPharmacyId(Long pharmId);
}
