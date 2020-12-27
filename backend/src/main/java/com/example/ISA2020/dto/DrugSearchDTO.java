package com.example.ISA2020.dto;

public class DrugSearchDTO {
	
	private Long id;

    private String name;

    private String code;
    
    private int quantity;
    
    private PharmacyDTO pharmacyDTO;

    
    
    
	public DrugSearchDTO() {
		super();
	}

	public DrugSearchDTO(Long id, String name, String code, int quantity, PharmacyDTO pharmacyDTO) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.pharmacyDTO = pharmacyDTO;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public PharmacyDTO getPharmacyDTO() {
		return pharmacyDTO;
	}

	public void setPharmacyDTO(PharmacyDTO pharmacyDTO) {
		this.pharmacyDTO = pharmacyDTO;
	}

	
	
    
    
}
