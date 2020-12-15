package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.HospitalDTO;
import com.example.ISA2020.entity.Hospital;
import com.example.ISA2020.repository.HospitalRepository;
import com.example.ISA2020.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {
	
	
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Override
	public Hospital findById(Long id) {
		return hospitalRepository.findOneById(id);
	}

	@Override
	public Hospital findOneByName(String name) {
		return hospitalRepository.findByName(name);
	}
	
	@Override
	public Hospital findOneByApiKey(String apiKey) {
		return hospitalRepository.findByApiKey(apiKey);
	}


	@Override
	public Hospital createHospital(HospitalDTO hospitalDTO) {
		if (hospitalRepository.findByName(hospitalDTO.getName()) != null) {
            return null;
        } //findByName vraca null ako ga nadje
		
		Hospital newHospital = new Hospital(
				hospitalDTO.getName(),
				hospitalDTO.getCity(),
				hospitalDTO.getAddress());
		
		List<Hospital> hospitals = hospitalRepository.findAll();
		
		for(Hospital h : hospitals) {
			if(newHospital.getApiKey() == h.getApiKey()) {
				String newString = randomStringGenerator();
				newHospital.setApiKey(newString);
				System.out.println("Izgenerisao je postojeci apiKey. Funkcija je pozvana jos jednom.");
			}
		}
		
		return hospitalRepository.save(newHospital);
				
	}
	
	@Override
	public List<Hospital> getAllHospitals() {
		return hospitalRepository.findAll();
	}
	
	@Override
	public List<Hospital> findAllHospitalsInOneCity(String city) {
		List<Hospital> hospitals = hospitalRepository.findAll();
		List<Hospital> hospitalsInOneCity = new ArrayList<>();
		for(Hospital h : hospitals) {
			if(h.getCity().equals(city)) {
				hospitalsInOneCity.add(h);
			}
		}
		return hospitalsInOneCity;
	}
	
	
	public String randomStringGenerator() {
		 
	    int length = 15;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(generatedString);
	    
	    return generatedString;
	}
	
}
