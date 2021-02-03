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
	
	@Override
	public List<DrugPriceAndPharmacyDTO> getAllDrugPriceByDrugName(String drugName) {
		
		List<DrugPrice> drugPrices = drugPriceRepo.findAll();
		
		List<DrugPriceAndPharmacyDTO> drugPricesDTO = new ArrayList<>();
		
		
		
		for(DrugPrice d : drugPrices) {
			if(d.getDrug().getName().toLowerCase().contains(drugName.toLowerCase())) { //contains da bi proverio bilo koji string a ne samo ceo naziv leka
				DrugPriceAndPharmacyDTO dto = new DrugPriceAndPharmacyDTO(); 	//tipa ako upisemo Bruf da nadje Brufen
				dto.setDrugName(d.getDrug().getName());
				dto.setDrugCode(d.getDrug().getCode());
				dto.setPharmacyName(d.getPharmacy().getName());
				dto.setPrice(d.getPrice());
				
				drugPricesDTO.add(dto);
			}
		}
		
		return drugPricesDTO;
	}
	
	@Override
	public List<DrugPriceAndPharmacyDTO> getAllDrugPriceByDrugCode(String drugCode) {
		
		List<DrugPrice> drugPrices = drugPriceRepo.findAll();
		
		List<DrugPriceAndPharmacyDTO> drugPricesDTO = new ArrayList<>();
		
		
		
		for(DrugPrice d : drugPrices) {
			if(d.getDrug().getCode().toLowerCase().contains(drugCode.toLowerCase())) { 
				DrugPriceAndPharmacyDTO dto = new DrugPriceAndPharmacyDTO(); 	
				dto.setDrugName(d.getDrug().getName());
				dto.setDrugCode(d.getDrug().getCode());
				dto.setPharmacyName(d.getPharmacy().getName());
				dto.setPrice(d.getPrice());
				
				drugPricesDTO.add(dto);
			}
		}
		
		return drugPricesDTO;
	}
	
	@Override
	public List<DrugPriceAndPharmacyDTO> getAllDrugPriceByPharmacyName(String pharmacyName) {
		
		List<DrugPrice> drugPrices = drugPriceRepo.findAll();
		
		List<DrugPriceAndPharmacyDTO> drugPricesDTO = new ArrayList<>();
		
		
		
		for(DrugPrice d : drugPrices) {
			if(d.getPharmacy().getName().toLowerCase().contains(pharmacyName.toLowerCase())) { 
				DrugPriceAndPharmacyDTO dto = new DrugPriceAndPharmacyDTO(); 	
				dto.setDrugName(d.getDrug().getName());
				dto.setDrugCode(d.getDrug().getCode());
				dto.setPharmacyName(d.getPharmacy().getName());
				dto.setPrice(d.getPrice());
				
				drugPricesDTO.add(dto);
			}
		}
		
		return drugPricesDTO;
	}
	
}
