package com.example.ISA2020.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ISA2020.entity.Hospital;
import com.example.ISA2020.repository.HospitalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;






@RunWith(SpringRunner.class)
//@WebMvcTest(value = HospitalController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class HospitalControllerTest {
	
	private static final String URL_PREFIX = "/api/auth/hospital";
	public static final Long DB_ID = 1L;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private HospitalRepository hospitalRepo;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void testListOfHospitals() throws Exception {
		HashMap<String, Object> sessionattr = new HashMap<String, Object>();
		sessionattr.put("id", DB_ID);
		
		List<Hospital> listOfHospitals = new ArrayList<>();
		
//		Hospital h1 = new Hospital(1L, "Name1", "City1", "Address1", "ApiKey1");
		listOfHospitals.add(new Hospital(1L, "Name1", "City1", "Address1", "ApiKey1"));
		listOfHospitals.add(new Hospital(2L, "Name2", "City2", "Address2", "ApiKey2"));
		listOfHospitals.add(new Hospital(3L, "Name3", "City3", "Address3", "ApiKey3"));
		listOfHospitals.add(new Hospital(4L, "Name4", "City4", "Address4", "ApiKey4"));
		listOfHospitals.add(new Hospital(5L, "Name5", "City5", "Address5", "ApiKey5"));
		
		Mockito.when(hospitalRepo.findAll()).thenReturn(listOfHospitals);
		
		String url = "/api/auth/hospital/getAll";
		
		mockMvc.perform(get(URL_PREFIX + "/getAll").sessionAttrs(sessionattr)).andExpect(status().isOk());
		
		/*
		 * MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
		 * .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn(); String content =
		 * mvcResult.getResponse().getContentAsString(); Hospital[] hospitalList =
		 * super.mapFromJson(content, Hospital[].class); assertTrue(hospitalList.length >
		 * 0);
		 */
	}
	
}
