package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.DermatologistWorkHours;
import com.example.ISA2020.enumeration.EntityStatus;

public interface DermWorkHoursRepository extends JpaRepository<DermatologistWorkHours, Long> {

	List<DermatologistWorkHours> findByPharmacyIdAndStatusNot(Long id, EntityStatus status);

	DermatologistWorkHours findByPharmacyIdAndDermatologistId(Long pharmacyId, Long id);

	List<DermatologistWorkHours> findByPharmacyIdNotAndStatusNot(Long id, EntityStatus status);

	List<DermatologistWorkHours> findByDermatologistIdAndStatusNot(Long id, EntityStatus status);

	List<DermatologistWorkHours> findByStatus(EntityStatus status);

}
