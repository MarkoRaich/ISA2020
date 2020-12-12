package com.example.ISA2020.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.Drug;

public class DrugDTO {
	
	private Long id;

    private String name;

    
	public DrugDTO() {
		super();
	}

	public DrugDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public DrugDTO(Drug drug) {
		this(drug.getId(), drug.getName());
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
    
}
