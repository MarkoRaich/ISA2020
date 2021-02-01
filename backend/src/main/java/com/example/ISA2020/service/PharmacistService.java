package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.users.Pharmacist;

public interface PharmacistService {

	Pharmacist findById(Long id);
	
	Pharmacist findByUsername(String username);
	
	List<Pharmacist> getAllPharmacists();
}
