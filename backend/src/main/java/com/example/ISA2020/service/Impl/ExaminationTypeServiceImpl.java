package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ExaminationTypeDTO;
import com.example.ISA2020.entity.ExaminationType;
import com.example.ISA2020.repository.ExaminationTypeRepository;
import com.example.ISA2020.service.ExaminationTypeService;


@Service
public class ExaminationTypeServiceImpl implements ExaminationTypeService {

	 @Autowired
	 private ExaminationTypeRepository examinationTypeRepository;
	 
	@Override
	public List<ExaminationTypeDTO> findAllTypesInClinic(Long pharmId) {
		
		 return convertToDTO(examinationTypeRepository.findByPharmacyId(pharmId));
	}

	
	 private List<ExaminationTypeDTO> convertToDTO(List<ExaminationType> examinationTypes) {
	        if (examinationTypes == null || examinationTypes.isEmpty()) {
	            return new ArrayList<>();
	        }
	        List<ExaminationTypeDTO> examinationTypeDTOS = new ArrayList<>();
	        for (ExaminationType examinationType : examinationTypes) {
	            examinationTypeDTOS.add(new ExaminationTypeDTO(examinationType));
	        }
	        return examinationTypeDTOS;
	    }


	@Override
	public ExaminationType findById(Long id) {
		
		return examinationTypeRepository.findOneById(id);
	}
}
