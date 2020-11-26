package com.example.ISA2020;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.lang.Assert;

@SpringBootApplication
public class Isa2020Application {

	public static void main(String[] args) {
		SpringApplication.run(Isa2020Application.class, args);

	}

}
