package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.service.PharmacistService;

@Service
public class PharmacistServiceImpl implements PharmacistService{
	
	@Autowired
	private PharmacistRepository pharmacistRepo;
	
	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepo.findOneById(id);
	}

	@Override
	public Pharmacist findByUsername(String username) {
		return pharmacistRepo.findOneByUsername(username);
	}

	@Override
	public List<Pharmacist> getAllPharmacists() {
		return pharmacistRepo.findAll();
	}

	
}
