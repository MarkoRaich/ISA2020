package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.users.SystemAdmin;
import com.example.ISA2020.repository.SystemAdminRepository;
import com.example.ISA2020.service.SystemAdminService;

@Service
public class SystemAdminServiceImpl implements SystemAdminService{
	
	@Autowired
	private SystemAdminRepository systemAdminRepo;

	@Override
	public SystemAdmin findById(Long id) {
		return systemAdminRepo.findOneById(id);
	}

	@Override
	public SystemAdmin findByUsername(String username) {
		return systemAdminRepo.findOneByUsername(username); 
	}

	@Override
	public List<SystemAdmin> getAllSystemAdmins() {
		return systemAdminRepo.findAll();
	}

}
