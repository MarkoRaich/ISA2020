package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.UserStatus;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
	
	Pharmacist findOneById(Long id);

	Pharmacist findOneByUsername(String username);
	
	List<Pharmacist> findAll();

	List<Pharmacist> findByPharmacyIdAndStatusNot(Long id, UserStatus status);

	List<Pharmacist> findByPharmacyIdAndStatusNotAndFirstNameContainsIgnoringCaseAndLastNameContainsIgnoringCase(
			Long id, UserStatus status, String firstName, String lastName);

	Pharmacist findByIdAndStatusNot(Long id, UserStatus status);
}
