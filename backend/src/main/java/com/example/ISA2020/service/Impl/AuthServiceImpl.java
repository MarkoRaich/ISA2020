package com.example.ISA2020.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.LoggedInUserDTO;
import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.dto.UserTokenState;
import com.example.ISA2020.repository.AuthRepository;
import com.example.ISA2020.security.TokenUtils;
import com.example.ISA2020.security.auth.JwtAuthenticationRequest;
import com.example.ISA2020.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AuthRepository authorityRepository;

    //LOGIN METODA
    @Override
    public LoggedInUserDTO login(JwtAuthenticationRequest jwtAuthenticationRequest) {
        
    	 //System.out.println("username:" + jwtAuthenticationRequest.getUsername() + "\n password: " + jwtAuthenticationRequest.getPassword());
    	
    	final Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                jwtAuthenticationRequest.getUsername(),
                jwtAuthenticationRequest.getPassword()
            )
        );
        
        //System.out.println("korisnik nadjen");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //kreiranje tokena za korisnika
        String username = returnUsername(authentication.getPrincipal());
        if (username == null) {
            return null;
        }
        
        //System.out.println("generisanje tokena");
        
        String jwtToken = tokenUtils.generateToken(username);
        int expiresIn = tokenUtils.getExpiredIn();
        
        //System.out.println("token izgenerisan");
        
        return returnLoggedInUser(
            authentication.getPrincipal(),
            new UserTokenState(jwtToken, expiresIn)
        );
    }


    private LoggedInUserDTO returnLoggedInUser(Object object, UserTokenState userTokenState) {
        if (object instanceof Patient) {
            Patient patient = (Patient) object;
            return new LoggedInUserDTO(patient.getId(), patient.getUsername(), "PATIENT", userTokenState);
        } else if (object instanceof PharmacyAdmin) {
        	PharmacyAdmin pharmacyAdmin = (PharmacyAdmin) object;
        	return new LoggedInUserDTO(pharmacyAdmin.getId(), pharmacyAdmin.getUsername(), "PHARMACY_ADMIN", userTokenState);
        } 
        // OVAKO I ZA SVE OSTALE TIPOVE USERA!!

        return null;
 
    }
    
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
       
       if (object instanceof PharmacyAdmin) {
            return ((PharmacyAdmin) object).getUsername();
        } else if (object instanceof Patient) {
            return ((Patient) object).getUsername();
        }
       	//OVAKO ZA SVE OSTALE TIPOVE USERA!!
       
        return null;
    }

}
