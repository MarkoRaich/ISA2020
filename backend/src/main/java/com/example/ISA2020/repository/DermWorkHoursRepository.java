package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.DermWorkHours;
import com.example.ISA2020.enumeration.EntityStatus;

public interface DermWorkHoursRepository extends JpaRepository<DermWorkHours, Long> {

	List<DermWorkHours> findByPharmacyIdAndStatusNot(Long id, EntityStatus status);

	DermWorkHours findByPharmacyIdAndDermatologistId(Long pharmacyId, Long id);

}
