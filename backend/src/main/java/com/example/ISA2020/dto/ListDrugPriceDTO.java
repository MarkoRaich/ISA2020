package com.example.ISA2020.dto;

import java.util.List;

public class ListDrugPriceDTO {
	
	private List<DrugPriceDTO> pricelist;
	
	public ListDrugPriceDTO() {}
	
	public ListDrugPriceDTO(List<DrugPriceDTO> pricelist) {
		this.pricelist = pricelist;
	}

	public List<DrugPriceDTO> getPricelist() {
		return pricelist;
	}

	public void setPricelist(List<DrugPriceDTO> pricelist) {
		this.pricelist = pricelist;
	}
	
	
	
}
