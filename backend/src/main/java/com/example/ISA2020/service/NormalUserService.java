package com.example.ISA2020.service;

import com.example.ISA2020.entity.NormalUser;

public interface NormalUserService {
	
	NormalUser findById(Long id);
    
	NormalUser findOneByUsername(String username);
    
}
