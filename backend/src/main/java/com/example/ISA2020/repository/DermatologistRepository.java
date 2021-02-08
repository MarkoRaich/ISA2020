package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.dto.DermatologistDTO;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.UserStatus;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	
	

	Dermatologist findOneById(Long id);

	Dermatologist findOneByUsername(String username);
	
	List<Dermatologist> findAll();
	
	//List<Dermatologist> findByPharmaciesIdAndStatusNot(Long id, UserStatus status);

	List<Dermatologist> findByStatusNot(UserStatus status);

	List<Dermatologist> findByStatusNotAndFirstNameContainsIgnoringCaseAndLastNameContainsIgnoringCase(
			UserStatus status, String firstName, String lastName);
}
