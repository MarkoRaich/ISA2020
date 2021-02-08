package com.example.ISA2020.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.LoggedInUserDTO;
import com.example.ISA2020.dto.UserDTO;
import com.example.ISA2020.security.auth.JwtAuthenticationRequest;
import com.example.ISA2020.service.AuthService;
import com.example.ISA2020.service.UserService;


@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {


	@Autowired
    private AuthService authService;

	@Autowired
	private UserService userService;
    
    @PostMapping(value = "/login")
    public ResponseEntity<LoggedInUserDTO> login(@RequestBody JwtAuthenticationRequest authenticationRequest)
        throws AuthenticationException, IOException {
        try {
        	
            LoggedInUserDTO loggedInUserDTO = authService.login(authenticationRequest);

            if (loggedInUserDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
            	System.out.println("Ulogovan " + loggedInUserDTO.getRole() + " : " + loggedInUserDTO.getUsername());
                return new ResponseEntity<>(loggedInUserDTO, HttpStatus.OK);
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        
        System.out.println("authenticationException");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changePassword(@Valid @RequestBody UserDTO userDTO) {

    	
        Object newUser = userService.changePassword(userDTO);

        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
