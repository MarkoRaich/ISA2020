package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.DrugQuantityInPharmacy;
import com.example.ISA2020.entity.PharmacyDrugKey;
import com.example.ISA2020.repository.PharmacyDrugDetailsRepository;
import com.example.ISA2020.service.PharmacyDrugDetailsService;

@Service
public class PharmacyDrugDetailsServiceImpl implements PharmacyDrugDetailsService {
	
	@Autowired
    private PharmacyDrugDetailsRepository pharmacyDrugDetailsRepository;
	
	@Override
	public DrugQuantityInPharmacy findById(PharmacyDrugKey id) {
		return pharmacyDrugDetailsRepository.findOneById(id);
	}
	
	@Override
	public List<DrugQuantityInPharmacy> getAllPharmacyDrugDetails() {
		return pharmacyDrugDetailsRepository.findAll();
	}

	@Override
	public void save(DrugQuantityInPharmacy pharmacyDrugDetails) {
		pharmacyDrugDetailsRepository.save(pharmacyDrugDetails);
	}

}
