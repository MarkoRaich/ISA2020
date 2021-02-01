package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.Complaint;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.repository.ComplaintRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired 
	private PatientRepository patientRepository;

	@Override
	public Complaint findById(Long id) {
		return complaintRepository.findOneById(id);
	}

	@Override
	public List<Complaint> getAllComplaints() {
		return complaintRepository.findAll();
	}

	@Override
	public Complaint create(Complaint comp, Long patientId) {
		if(complaintRepository.findOneById(comp.getId()) != null) {
			return null;
		}
		
		Complaint newComplaint = new Complaint();
		newComplaint.setSubject(comp.getSubject());
		newComplaint.setMessage(comp.getMessage());
		newComplaint.setDoctorName(comp.getDoctorName());
		newComplaint.setExamination(comp.getExamination());
		//setPatient???
		Patient patient = patientRepository.findOneById(patientId);
		if(patient == null) {
			return null; //ili != ??
		}
		newComplaint.setPatient(patient);
		
		complaintRepository.save(newComplaint);
		
		return newComplaint;
		
	}
}
