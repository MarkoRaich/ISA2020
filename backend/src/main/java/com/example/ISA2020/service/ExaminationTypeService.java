package com.example.ISA2020.service;

import java.util.List;


import com.example.ISA2020.dto.ExaminationTypeDTO;
import com.example.ISA2020.entity.ExaminationType;

public interface ExaminationTypeService {

	List<ExaminationTypeDTO> findAllTypesInClinic(Long id);

	ExaminationType findById(Long id);

}
