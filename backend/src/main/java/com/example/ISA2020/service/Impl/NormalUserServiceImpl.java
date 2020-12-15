package com.example.ISA2020.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.NormalUserDTO;
import com.example.ISA2020.entity.users.NormalUser;
import com.example.ISA2020.repository.NormalUserRepository;
import com.example.ISA2020.service.AuthService;
import com.example.ISA2020.service.NormalUserService;


@Service
public class NormalUserServiceImpl implements NormalUserService {

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
    public NormalUserDTO createNormalUser(NormalUserDTO normalUserDTO) {
        if (normalUserRepository.findByUsername(normalUserDTO.getUsername()) != null) {
            return null;
        } //findByUsername vraca null ako ga nadje

        String hashedPassword = passwordEncoder.encode(normalUserDTO.getPassword());

        NormalUser newNormalUser = new NormalUser(
                normalUserDTO.getUsername(),
                hashedPassword,
                normalUserDTO.getFirstName(),
                normalUserDTO.getLastName(),
                normalUserDTO.getEmail(),
                normalUserDTO.getCountry(),
                normalUserDTO.getPhoneNumber(),
                normalUserDTO.getAddress(),
                normalUserDTO.getCity()
        );
        newNormalUser.setAuthorities(authService.findById(1L));
        return new NormalUserDTO(normalUserRepository.save(newNormalUser));
    }
    
    //??????
    @Override
    public NormalUser getUserLogin() {
       // Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username;
    	if (principal instanceof UserDetails) {
    	   username = ((UserDetails)principal).getUsername();
    	} else {
    	   username = principal.toString();
    	}
    
      //  System.out.println(currentUser.getName());
        try {
            NormalUser normalUser = normalUserRepository.findByUsername(username);
            if (normalUser != null) {
                return normalUser;
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
