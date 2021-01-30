package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	
	Complaint findOneById(Long id);
	
	List<Complaint> findAll();
}
