package com.example.ISA2020.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.repository.PharmacyAdminRepository;
import com.example.ISA2020.service.UserService;

//Izdvojen servis za sve korisnike aplikacije
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	 @Autowired
	 private PatientRepository patientRepository;
	 
	 @Autowired
	 private PharmacyAdminRepository pharmacyAdminRepository;
	
	
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
	private UserDetails searchUserInAllRepositories(String username) {
		
		 try {
	            Patient patient = patientRepository.findByUsername(username);
	            if (patient != null) {
	                return patient;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 try {
	            PharmacyAdmin pharmacyAdmin = pharmacyAdminRepository.findByUsername(username);
	            if (pharmacyAdmin != null) {
	                return pharmacyAdmin;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		return null;
	}
	//dopuni sa trazenjem po svim repozitorijumima case patient, case admin itd...
}
