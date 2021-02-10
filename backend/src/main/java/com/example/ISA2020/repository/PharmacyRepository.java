package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
	
	Pharmacy findOneById(Long id);
	
    Pharmacy findOneByName(String name);
    
    List<Pharmacy> findAll();

	Pharmacy findByAddressIgnoringCase(String address);
	
	List<Pharmacy> findByOrderByAddressAsc();
	
	List<Pharmacy> findByOrderByNameAsc();
	
	List<Pharmacy> findByOrderByRatingAsc();
	
	List<Pharmacy> findByOrderByRating();
	
	Pharmacy findByPharmacistsId(Long id);
}
