package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugPriceAndPharmacyDTO;
import com.example.ISA2020.entity.DrugPrice;
import com.example.ISA2020.repository.DrugPriceRepository;
import com.example.ISA2020.service.DrugPriceService;

@Service
public class DrugPriceServiceImpl implements DrugPriceService {
	
	@Autowired
	private DrugPriceRepository drugPriceRepo;
	
	@Override
	public DrugPrice findById(Long id) {
		return drugPriceRepo.findOneById(id);
	}

	@Override
	public List<DrugPrice> getAllDrugPrices() {
		return drugPriceRepo.findAll();
	}
	
	@Override
	public List<DrugPriceAndPharmacyDTO> getAllDrugPriceAndPharmacyDTO() {
		List<DrugPrice> drugPrices = drugPriceRepo.findAll();
		
		List<DrugPriceAndPharmacyDTO> drugPricesDTO = new ArrayList<>();
		
		for(DrugPrice d : drugPrices) {
			DrugPriceAndPharmacyDTO dto = new DrugPriceAndPharmacyDTO();
			dto.setDrugName(d.getDrug().getName());
			dto.setDrugCode(d.getDrug().getCode());
			dto.setPharmacyName(d.getPharmacy().getName());
			dto.setPrice(d.getPrice());
			
			drugPricesDTO.add(dto);
		}
		
		return drugPricesDTO;
	}
	
}
