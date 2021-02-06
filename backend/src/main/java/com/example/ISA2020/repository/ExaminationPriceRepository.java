package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.ExaminationPrice;

public interface ExaminationPriceRepository extends JpaRepository<ExaminationPrice,	Long> {
	
	ExaminationPrice findOneById(Long id);
	
	List<ExaminationPrice> findAll();
	
	List<ExaminationPrice> findByOrderByPrice();
	
	List<ExaminationPrice> findByOrderByIntervalStartDateTime();
	
	//List<ExaminationPrice> findByOrderByIntervalStartDateTimeAndEndDateTime();
}
