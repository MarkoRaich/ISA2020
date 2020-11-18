package com.example.ISA2020.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.NormalUser;
import com.example.ISA2020.repository.NormalUserRepository;
import com.example.ISA2020.service.AuthService;
import com.example.ISA2020.service.NormalUserService;


@Service
public class NormalUserServiceImpl implements UserDetailsService, NormalUserService {

    @Autowired
    private AuthService authService;

    @Autowired
    private NormalUserRepository normalUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Override
    public NormalUser findById(Long id){
    	return normalUserRepository.findOneById(id);
    }

    @Override
    public NormalUser findOneByUsername(String username) {
        return normalUserRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NormalUser normalUser = findOneByUsername(username);
        if (normalUser == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        } else {
            return normalUser;
        }
    }
}
