package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.users.SystemAdmin;

public interface SystemAdminService {
	
	SystemAdmin findById(Long id);
	
	SystemAdmin findByUsername(String username);
	
	List<SystemAdmin> getAllSystemAdmins();
	
}
