package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.Complaint;

public interface ComplaintService {
	
	Complaint findById(Long id);
	
	List<Complaint> getAllComplaints();
}
