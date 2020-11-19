package com.example.ISA2020.service;

import java.util.Set;

import com.example.ISA2020.dto.LoggedInUserDTO;
import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.security.auth.JwtAuthenticationRequest;


public interface AuthService {

    Set<Authority> findById(Long id);
    
    Set<Authority> findByName(String name);
    
    LoggedInUserDTO login(JwtAuthenticationRequest jwtAuthenticationRequest);

}