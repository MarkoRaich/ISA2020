package com.example.ISA2020.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugDTO;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.repository.DrugRepository;
import com.example.ISA2020.service.DrugService;

@Service
public class DrugServiceImpl implements DrugService {
	
	@Autowired
    private DrugRepository drugRepository;

	@Override
	public Drug findById(Long id) {
		return drugRepository.findOneById(id);
	}

	@Override
	public Drug findByName(String name) {
		return drugRepository.findOneByName(name);
	}

	@Override
	public Drug createDrug(DrugDTO drugDTO) {
		if (drugRepository.findOneByName(drugDTO.getName()) != null) {
            return null;
        } //findByName vraca null ako ga nadje
		
		Drug newDrug = new Drug(drugDTO.getName());
		
		return drugRepository.save(newDrug);
	}
	

}
