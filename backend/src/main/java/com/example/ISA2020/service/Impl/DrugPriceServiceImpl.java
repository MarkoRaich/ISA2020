package com.example.ISA2020.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugPriceAndPharmacyDTO;
import com.example.ISA2020.dto.DrugPriceDTO;
import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.ListDrugPriceDTO;
import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.DrugPrice;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.repository.DrugPriceRepository;
import com.example.ISA2020.service.DrugPriceService;
import com.example.ISA2020.service.DrugService;
import com.example.ISA2020.service.PharmacyService;

@Service
public class DrugPriceServiceImpl implements DrugPriceService {
	
	@Autowired
	private DrugPriceRepository drugPriceRepo;
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Override
	public DrugPrice findById(Long id) {
		return drugPriceRepo.findOneById(id);
	}

	@Override
	public List<DrugPrice> getAllDrugPrices() {
		return drugPriceRepo.findAll();
	}
	
	@Override
	public List<DrugPriceDTO> getAddDrugPricesForPharmacy(Long pharmId) {
		
		return convertToDTO(this.drugPriceRepo.findAllByPharmacyId(pharmId));
	}
	
	private List<DrugPriceDTO> convertToDTO(List<DrugPrice> drugPrices){
		
		 List<DrugPriceDTO> returnList = new ArrayList<DrugPriceDTO>();
		 
		 for(DrugPrice drugP: drugPrices) {
			 returnList.add(new DrugPriceDTO(drugP));
		 }
		 
		 return returnList;
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
	
	//3.1 -------------------------------------------------------------------------------------------
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
	//###################################################################################### 3.1
	
	
	//3.9 -------------------------------------------------------------------------------------------
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllDrugPriceByPharmacyCityForPatient(String pharmacyAddress) {
		
		List<DrugPrice> drugPrices = drugPriceRepo.findAll();
		
		List<DrugPricePharmacyNameAddressRatingDTO> drugPricesDTO = new ArrayList<>();
		
		
		
		for(DrugPrice d : drugPrices) {
			if(d.getPharmacy().getAddress().toLowerCase().contains(pharmacyAddress.toLowerCase())) { 
				DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO(); 	
				dto.setPharmacyName(d.getPharmacy().getName());
				dto.setPharmacyAddress(d.getPharmacy().getAddress());
				dto.setPharmacyRating(d.getPharmacy().getRating());
				
				drugPricesDTO.add(dto);
			}
		}
		
		return drugPricesDTO;
	}
	
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllDrugPriceByPharmacyNameForPatient(String pharmacyName) {
		
		List<DrugPrice> drugPrices = drugPriceRepo.findAll();
		
		List<DrugPricePharmacyNameAddressRatingDTO> drugPricesDTO = new ArrayList<>();
		
		
		
		for(DrugPrice d : drugPrices) {
			if(d.getPharmacy().getName().toLowerCase().contains(pharmacyName.toLowerCase())) { 
				DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO(); 	
				dto.setPharmacyName(d.getPharmacy().getName());
				dto.setPharmacyAddress(d.getPharmacy().getAddress());
				dto.setPharmacyRating(d.getPharmacy().getRating());
				
				drugPricesDTO.add(dto);
			}
		}
		
		return drugPricesDTO;
	}

	@Override
	public DrugPriceDTO createPricelist(Long pharmId, ListDrugPriceDTO pricelist) {
		
		List<DrugPrice> drugPrices = new ArrayList<>();
		
		
		List<DrugPrice> allDrugPrices = drugPriceRepo.findAllByPharmacyId(pharmId);
		
		
		LocalDate localStartDate = getDate(pricelist.getPricelist().get(0).getStartDate());
		LocalDate localEndDate = getDate(pricelist.getPricelist().get(0).getEndDate());
		
        LocalDateTime startDateTime = getLocalDateTime(localStartDate, pricelist.getPricelist().get(0).getStartDate());
        LocalDateTime endDateTime = getLocalDateTime(localEndDate, pricelist.getPricelist().get(0).getEndDate());
        
        DateTimeInterval interval = new DateTimeInterval(startDateTime, endDateTime);
		
        //proveravanje da li je interval u konfliktu sa drugim cenovnicima
        for(DrugPrice drugPrice: allDrugPrices) {
			if(!(drugPrice.getInterval().getStartDateTime().isBefore(interval.getStartDateTime()) && drugPrice.getInterval().getEndDateTime().isBefore(interval.getStartDateTime()) )) {
				if(!(drugPrice.getInterval().getStartDateTime().isAfter(interval.getEndDateTime()) && drugPrice.getInterval().getEndDateTime().isAfter(interval.getEndDateTime()) ) ) {
					return null;
				} 
			}
		}
        
		for(DrugPriceDTO drugPriceDTO: pricelist.getPricelist()) {
			
			Drug drug = drugService.findById(drugPriceDTO.getDrug().getId());
			
			Pharmacy pharmacy = pharmacyService.findById(pharmId);
			
			
			DrugPrice newDrugPrice = new DrugPrice(drugPriceDTO.getPrice(), interval, drug, pharmacy);
			drugPrices.add(newDrugPrice);
			
			drugPriceRepo.save(newDrugPrice);
			
		}
		
		return new DrugPriceDTO(drugPrices.get(0)); 
	}
	
	
	 private LocalDateTime getLocalDateTime(LocalDate date, String time) throws DateTimeParseException {
	        LocalTime localTime = LocalTime.parse(time.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
	        return LocalDateTime.of(date, localTime);
	    }

	    private LocalDate getDate(String date) throws DateTimeParseException {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        return LocalDate.parse(date.substring(0, 10), formatter);
	    }

		@Override
		public DrugPriceDTO changeDrugPriceInPharmacy(@Valid DrugPriceDTO drugDTO, Pharmacy pharmacy) {

			DrugPrice drugP = drugPriceRepo.findOneById(drugDTO.getId());
			
			drugP.setPrice(drugDTO.getPrice());
			
			
			return new DrugPriceDTO(drugPriceRepo.save(drugP));

		}

}
