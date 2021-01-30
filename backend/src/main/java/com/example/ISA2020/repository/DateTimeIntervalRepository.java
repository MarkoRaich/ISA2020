package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.DateTimeInterval;

public interface DateTimeIntervalRepository extends JpaRepository<DateTimeInterval, Long>{
	
	DateTimeInterval findOneById(Long id);
	
	List<DateTimeInterval> findAll();
}
