package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.UserStatus;

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
	
	List<Pharmacy> findByIdAndNameContainsIgnoringCaseAndAddressContainsIgnoringCase(
			Long id, String name, String address);
}
