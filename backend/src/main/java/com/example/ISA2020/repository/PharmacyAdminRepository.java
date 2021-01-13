package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.PharmacyAdmin;

public interface PharmacyAdminRepository extends JpaRepository<PharmacyAdmin, Long> {

	List<PharmacyAdmin> findAll();
	
	PharmacyAdmin findByUsername(String username);
}
