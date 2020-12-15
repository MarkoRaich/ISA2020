package com.example.ISA2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.NormalUserDTO;
import com.example.ISA2020.entity.users.NormalUser;
import com.example.ISA2020.service.NormalUserService;

@RestController
@RequestMapping(value = "/api/auth/user")
public class NormalUserController {

    @Autowired
    private NormalUserService normalUserService;

    @PostMapping(value = "/register")
    public ResponseEntity<NormalUserDTO> register(@RequestBody NormalUserDTO normalUser) {
        try {
            NormalUserDTO normalUserDTO = normalUserService.createNormalUser(normalUser);
            if (normalUserDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(normalUserDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<NormalUserDTO> getUser(@PathVariable Long id){
		NormalUser user = normalUserService.findById(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		NormalUserDTO userDTO = new NormalUserDTO(user);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
    
    
}
