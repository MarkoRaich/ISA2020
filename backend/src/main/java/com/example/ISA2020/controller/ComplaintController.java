package com.example.ISA2020.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.ComplaintDTO;
import com.example.ISA2020.dto.PatientDTO;
import com.example.ISA2020.entity.Complaint;
import com.example.ISA2020.service.ComplaintService;
import com.example.ISA2020.service.PatientService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/complaint")
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired 
	private PatientService patientService;
	
	@PostMapping("/create")
	public ResponseEntity<Complaint> create(@RequestBody ComplaintDTO complaintDTO){
		try {
			Complaint comp = complaintService.create(complaintDTO);
			if(comp == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Complaint>(comp, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ComplaintDTO>> getAll() {
		List<ComplaintDTO> complaints = complaintService.getAllComplaints();
		if(complaints == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ComplaintDTO>>(complaints, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Complaint> getOne(@PathVariable Long id) {
		Complaint comp = complaintService.findById(id);
		if(comp == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Complaint>(comp, HttpStatus.OK);
	}
	
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<List<Complaint>> getByPatientId(@PathVariable Long id) {
		PatientDTO patient = patientService.findById(id);
		if(patient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<ComplaintDTO> complaints = complaintService.getAllComplaints();
		List<Complaint> patientComplaints = new ArrayList<>();
		
//		for(ComplaintDTO c : complaints) {
//			if(c.getPatient() != null) {
//				if(c.getPatient().getId() == id) {
//					patientComplaints.add(c);
//				}
//			}else {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
//		}
//		
		return new ResponseEntity<List<Complaint>>(patientComplaints, HttpStatus.OK);
	}
	
}
