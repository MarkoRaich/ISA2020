package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.ConsultationPrice;

public interface ConsultationPriceRepository extends JpaRepository<ConsultationPrice, Long>{
	
	ConsultationPrice findOneById(Long id);
	
	List<ConsultationPrice> findAll();
	
	List<ConsultationPrice> findByOrderByPrice();
	
	List<ConsultationPrice> findByOrderByIntervalStartDateTime();
	
	List<ConsultationPrice> findByPharmacyIdOrderByPrice(Long id);
	
	List<ConsultationPrice> findByPharmacyIdOrderByPharmacyRating(Long id);
	
	List<ConsultationPrice> findByOrderByPharmacyRating();
	
	List<ConsultationPrice> findByPharmacyIdOrderByConsultationPharmacistRating(Long id);
}
