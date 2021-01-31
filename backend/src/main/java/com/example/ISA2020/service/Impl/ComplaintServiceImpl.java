package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Complaint;
import com.example.ISA2020.repository.ComplaintRepository;
import com.example.ISA2020.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;

	@Override
	public Complaint findById(Long id) {
		// TODO Auto-generated method stub
		return complaintRepository.findOneById(id);
	}

	@Override
	public List<Complaint> getAllComplaints() {
		// TODO Auto-generated method stub
		return complaintRepository.findAll();
	}
}
