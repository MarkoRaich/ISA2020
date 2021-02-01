package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.SystemAdmin;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long>{
	
	SystemAdmin findOneById(Long id);

	SystemAdmin findOneByUsername(String username);
	
	List<SystemAdmin> findAll();
}
