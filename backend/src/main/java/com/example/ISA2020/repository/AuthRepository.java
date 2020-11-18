package com.example.ISA2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.Authority;


public interface AuthRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);

}
