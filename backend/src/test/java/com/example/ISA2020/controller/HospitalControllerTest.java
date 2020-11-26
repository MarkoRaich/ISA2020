package com.example.ISA2020.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import com.example.ISA2020.dto.HospitalDTO;
import com.example.ISA2020.entity.Hospital;
import com.example.ISA2020.service.HospitalService;



@RunWith(SpringRunner.class)
@WebMvcTest(value = HospitalController.class)
public class HospitalControllerTest {
	
	private static final String URL_PREFIX = "/api/auth/hospital";
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	/*
	 * @MockBean private HospitalService hospitalService;
	 */
	
	Hospital hospital1 = new Hospital("VMA", "Beograd", "Neka Adresa");
	Hospital hospital2 = new Hospital("VMA2", "Beograd", "Neka Adresa");
	
	//HospitalDTO hospitalDTO = new HospitalDTO(1, "VMA", "Beograd", "Neka Adresa", Mockito.anyString());
	
	@Test
	public void registerHospital(@RequestBody HospitalDTO hospitalDTO) throws Exception {
			
	}
	
}
