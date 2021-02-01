package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.users.Dermatologist;

public interface DermatologistService {
	
	Dermatologist findById(Long id);
	
	Dermatologist findByUsername(String username);
	
	List<Dermatologist> getAllDermatologists();
}
