package com.example.ISA2020.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class RabbitSingletonSingleton {
	
	@Bean
	@Scope("singleton")
	public RabbitSingleton RabbitSingletonSingleton() {
		return new RabbitSingleton();
	}
}
