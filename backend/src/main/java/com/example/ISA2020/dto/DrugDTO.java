package com.example.ISA2020.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.Drug;

public class DrugDTO {
	
	private Long id;

    private String name;
    
    private String code;

    
	public DrugDTO() {
		super();
	}

	public DrugDTO(Long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	public DrugDTO(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public DrugDTO(Drug drug) {
		this(drug.getId(), drug.getName(), drug.getCode());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
