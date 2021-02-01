package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.users.Supplier;

public interface SupplierService {
	
	Supplier findById(Long id);
	
	Supplier findByUsername(String username);
	
	List<Supplier> getAllSuppliers();
}
