package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
    private PharmacyRepository pharmacyRepository;
	
	
	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findOneById(id);
	}

	@Override
	public Pharmacy findByName(String name) {
		return pharmacyRepository.findOneByName(name);
	}

	@Override
	public Pharmacy createPharmacy(PharmacyDTO pharmacyDTO) {
		if (pharmacyRepository.findOneByName(pharmacyDTO.getName()) != null) {
            return null;
        } //findByName vraca null ako ga nadje
		
		Pharmacy newPharmacy = new Pharmacy(pharmacyDTO.getName(), pharmacyDTO.getAddress());
		
		return pharmacyRepository.save(newPharmacy);
	}

	@Override
	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepository.findAll();
	}

}
