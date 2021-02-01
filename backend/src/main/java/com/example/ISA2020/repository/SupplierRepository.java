package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

	Supplier findOneById(Long id);

	Supplier findOneByUsername(String username);
	
	List<Supplier> findAll();
}
