package com.example.ISA2020.repository;

import static com.example.ISA2020.constants.HospitalConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.ISA2020.entity.Hospital;
import com.jayway.jsonpath.internal.Path;

import io.jsonwebtoken.lang.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class HospitalRepositoryUnitTests {
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
    private TestEntityManager entityManager;
	
	
	@Test
	public void testFindOneById_Success() {
		Hospital hospital = hospitalRepository.findOneById(HOSPITAL_ID);
		assertNotNull(hospital);
		assertEquals(HOSPITAL_ID, hospital.getId());
		
	}
	
	@Test
	public void testFindByName_Success() {
		Hospital hospital = hospitalRepository.findByName(HOSPITAL_NAME);
		assertNotNull(hospital);
		assertEquals(HOSPITAL_NAME, hospital.getName());
		
	}
	
	
	
	
}
