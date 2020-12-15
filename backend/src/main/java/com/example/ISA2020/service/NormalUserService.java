package com.example.ISA2020.service;

import com.example.ISA2020.dto.NormalUserDTO;
import com.example.ISA2020.entity.users.NormalUser;

public interface NormalUserService {
	
	NormalUser findById(Long id);
    
	NormalUser findOneByUsername(String username);
	
	NormalUserDTO createNormalUser(NormalUserDTO normalUser);
	
	NormalUser getUserLogin();
    
}
