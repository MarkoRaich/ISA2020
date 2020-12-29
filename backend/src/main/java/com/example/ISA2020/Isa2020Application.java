package com.example.ISA2020;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.ISA2020.files.FileStorageProperties;
import com.example.ISA2020.service.DrugAvailibilityService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class Isa2020Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Isa2020Application.class, args);

	}

}
