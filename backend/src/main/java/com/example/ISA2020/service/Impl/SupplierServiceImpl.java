package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.users.Supplier;
import com.example.ISA2020.repository.SupplierRepository;
import com.example.ISA2020.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepo;
	
	@Override
	public Supplier findById(Long id) {
		return supplierRepo.findOneById(id);
	}

	@Override
	public Supplier findByUsername(String username) {
		return supplierRepo.findOneByUsername(username);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierRepo.findAll();
	}

}
