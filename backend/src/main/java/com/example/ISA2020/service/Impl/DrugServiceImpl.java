package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

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
		//if (drugRepository.findOneByCode(drugDTO.getCode()) != null) {
        //    return null;
       // } //findByName vraca null ako ga nadje
		
		Drug newDrug = new Drug(drugDTO.getName(), drugDTO.getCode());
		
		return drugRepository.save(newDrug);
	}


	@Override
	public List<DrugDTO> getAllDrugs() {
		List<Drug> drugs = drugRepository.findAll();
		List<DrugDTO> drugsDTO = convertToDTO(drugs);
		return drugsDTO;
	}
	
	public List<DrugDTO> convertToDTO(List<Drug> drugs) {
		List<DrugDTO> drugsDTO = new ArrayList<>();
		for(Drug d : drugs) {
			DrugDTO dto = new DrugDTO();
			dto.setId(d.getId());
			dto.setName(d.getName());
			dto.setCode(d.getCode());
			dto.setComposition(d.getComposition());
			dto.setForm(d.getForm().toString());
			dto.setManufacturer(d.getManufacturer());
			dto.setNotes(d.getNotes());
			dto.setPrescription(d.getPrescription().toString());
			dto.setType(d.getType().toString());

			drugsDTO.add(dto);
		}
		return drugsDTO;
	}
	

}
