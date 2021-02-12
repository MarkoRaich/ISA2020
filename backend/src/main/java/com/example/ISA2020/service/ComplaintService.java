package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.dto.ComplaintDTO;
import com.example.ISA2020.entity.Complaint;

public interface ComplaintService {
	
	Complaint findById(Long id);
	
	List<ComplaintDTO> getAllComplaints();
	
	//Complaint create(Complaint comp, Long patientId);

	Complaint create(ComplaintDTO complaintDTO);
}
