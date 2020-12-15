package com.example.ISA2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.users.NormalUser;

public interface NormalUserRepository extends JpaRepository<NormalUser, Long> {

	NormalUser findOneById(Long id);
	
    NormalUser findByUsername(String username);

}