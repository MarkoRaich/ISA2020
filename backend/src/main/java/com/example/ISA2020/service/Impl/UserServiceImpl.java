package com.example.ISA2020.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ISA2020.repository.NormalUserRepository;
import com.example.ISA2020.service.UserService;

//Izdvojen servis za sve korisnike aplikacije
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	 @Autowired
	 private NormalUserRepository normalUserRepository;
	
	
	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetails userDetails = searchUserInAllRepositories(username);
		if (userDetails != null) {
            return userDetails;
        }
        throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
	}

	//trazi Usera u svim repozitorijumima
	//dopuni sa trazenjem po svim repozitorijumima case patient, case admin itd...
	private UserDetails searchUserInAllRepositories(String username) {
		
		return normalUserRepository.findByUsername(username);
	}

}
