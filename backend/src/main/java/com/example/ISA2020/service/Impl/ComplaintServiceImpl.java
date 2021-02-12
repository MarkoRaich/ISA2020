package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.ComplaintDTO;
import com.example.ISA2020.entity.Complaint;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.repository.ComplaintRepository;
import com.example.ISA2020.service.ComplaintService;
import com.example.ISA2020.service.PatientService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired 
	private PatientService patientService;

	@Override
	public Complaint findById(Long id) {
		return complaintRepository.findOneById(id);
	}

	@Override
	public List<ComplaintDTO> getAllComplaints() {
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Set<Complaint> complaints = patient.getComplaints();
		
		List<ComplaintDTO> dtos = new ArrayList<>();
		
		for(Complaint c : complaints) {
			ComplaintDTO dto = new ComplaintDTO();
			dto.setSubject(c.getSubject());
			dto.setMessage(c.getMessage());
			dto.setExamination(c.getExamination());
			dto.setDoctorName(c.getDoctorName());
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public Complaint create(ComplaintDTO complaintDTO) {
		
		Complaint newComplaint = new Complaint();
		newComplaint.setSubject(complaintDTO.getSubject());
		newComplaint.setMessage(complaintDTO.getMessage());
		newComplaint.setDoctorName(complaintDTO.getDoctorName());
		newComplaint.setExamination(complaintDTO.getExamination());
		//setPatient???
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null; //ili != ??
		}
		newComplaint.setPatient(patient);
		
		complaintRepository.save(newComplaint);
		
		return newComplaint;
		
	}
}
