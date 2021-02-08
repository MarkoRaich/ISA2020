package com.example.ISA2020.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.DrugQuantity;

public class DrugDTO {
	
	private Long id;

    private String name;
    
    private String code;

    private String manufacturer;
    
    private String composition;
    
    private String notes;
    
    private String form;
    
    private String type;
    
    private String prescription;
    
    
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
	
	
	
	public DrugDTO(String name, String code, String manufacturer, String composition, String notes, String form,
			String type, String prescription) {
		super();
		this.name = name;
		this.code = code;
		this.manufacturer = manufacturer;
		this.composition = composition;
		this.notes = notes;
		this.form = form;
		this.type = type;
		this.prescription = prescription;
	}
	
	

	public DrugDTO(Long id, String name, String code, String manufacturer, String composition, String notes,
			String form, String type, String prescription) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.manufacturer = manufacturer;
		this.composition = composition;
		this.notes = notes;
		this.form = form;
		this.type = type;
		this.prescription = prescription;
	}

	public DrugDTO(Drug drug) {
		this(drug.getId(), drug.getName(), drug.getCode(), drug.getManufacturer(), 
			 drug.getComposition(), drug.getNotes(), drug.getForm().toString(), drug.getType().toString(),
			 drug.getPrescription().toString() );
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	
    
}
