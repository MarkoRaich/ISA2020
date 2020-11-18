package com.example.ISA2020.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.NormalUser;
import com.example.ISA2020.repository.AuthRepository;
import com.example.ISA2020.security.TokenUtils;
import com.example.ISA2020.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AuthRepository authorityRepository;

    @Override
    public Set<Authority> findById(Long id) {
        Authority authority = this.authorityRepository.getOne(id);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public Set<Authority> findByName(String name) {
        Authority authority = this.authorityRepository.findByName(name);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        return authorities;
    }


    private String returnUsername(Object object) {
        if (object instanceof NormalUser) {
            return ((NormalUser) object).getUsername();
        } 
        return null;
    }

}
