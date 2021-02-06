package com.example.ISA2020.service.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.LoggedInUserDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.dto.UserTokenState;
import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.entity.users.Supplier;
import com.example.ISA2020.repository.AuthRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.security.TokenUtils;
import com.example.ISA2020.security.auth.JwtAuthenticationRequest;
import com.example.ISA2020.service.AuthService;
import com.example.ISA2020.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AuthRepository authorityRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PatientRepository patientRepository;
    

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
        } else if (object instanceof Supplier) {
        	Supplier supplier = (Supplier) object;
        	return new LoggedInUserDTO(supplier.getId(), supplier.getUsername(), "SUPPLIER", userTokenState);
        } else if (object instanceof Dermatologist) {
        	Dermatologist dermatologist = (Dermatologist) object;
        	return new LoggedInUserDTO(dermatologist.getId(), dermatologist.getUsername(), "DERMATOLOGIST", userTokenState);
        } else if (object instanceof Pharmacist) {
        	Pharmacist pharmacist = (Pharmacist) object;
        	return new LoggedInUserDTO(pharmacist.getId(), pharmacist.getUsername(), "PHARMACIST", userTokenState);
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
        } else if (object instanceof Supplier) {
            return ((Supplier) object).getUsername();
        } else if (object instanceof Dermatologist) {
            return ((Dermatologist) object).getUsername();
        } else if (object instanceof Pharmacist) {
            return ((Pharmacist) object).getUsername();
        }
       
        return null;
    }
    
    //REGISTRACIJA PACIJENTA
    @Override
    public PatientDTO registerPatient(PatientDTO patientDTO) {
        UserDetails userDetails = (UserDetails) userService.loadUserByUsername(patientDTO.getUsername());
        if (userDetails != null) {
            return null;
        }

        String hashedPassword = passwordEncoder.encode(patientDTO.getPassword());
        Set<Authority> authorities = findByName("ROLE_PATIENT");

        Patient newPatient = new Patient(patientDTO.getUsername(), hashedPassword, patientDTO.getFirstName(),
                patientDTO.getLastName(), patientDTO.getAddress(), patientDTO.getCity(),
                patientDTO.getPhoneNumber(), authorities);

        return new PatientDTO(patientRepository.save(newPatient));
    }

}
