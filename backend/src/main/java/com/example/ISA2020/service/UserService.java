package com.example.ISA2020.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.dto.UserDTO;

public interface UserService {
	
	 	UserDetails changePassword(UserDTO userDTO);

	    Object loadUserByUsername(String username);

	    boolean neverLoggedIn(String email);

}
