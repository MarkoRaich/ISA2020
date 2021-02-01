package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.controller.DermatologistRepository;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.service.DermatologistService;

@Service
public class DermatologistServiceImpl implements DermatologistService{
	
	@Autowired
	private DermatologistRepository dermatologistRepo;
	
	@Override
	public Dermatologist findById(Long id) {
		return dermatologistRepo.findOneById(id);
	}

	@Override
	public Dermatologist findByUsername(String username) {
		return dermatologistRepo.findOneByUsername(username);
	}

	@Override
	public List<Dermatologist> getAllDermatologists() {
		return dermatologistRepo.findAll();
	}

}
