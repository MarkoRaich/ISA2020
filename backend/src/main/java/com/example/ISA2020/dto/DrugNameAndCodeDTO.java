package com.example.ISA2020.dto;

import com.example.ISA2020.entity.Drug;

public class DrugNameAndCodeDTO {
	
	private String name;
    
    private String code;

    
	public DrugNameAndCodeDTO() {
		super();
	}

	public DrugNameAndCodeDTO(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
	public DrugNameAndCodeDTO(DrugDTO drugDTO) {
		this(drugDTO.getName(), drugDTO.getCode());
	}
	
	public DrugNameAndCodeDTO(Drug drug) {
		this(drug.getName(), drug.getCode());
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
    
    
}
