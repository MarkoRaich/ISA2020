package com.example.ISA2020.dto;

import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.Examination;

public class ExaminationDTO {
	
	private Long id;
	
	private String dermFirstName;
	
	private String dermLastName;
	
	private DateTimeInterval interval;
	
	private ExaminationTypeDTO type;
	
	
	public ExaminationDTO() {}


	public ExaminationDTO(Long id, String dermFirstName, String dermLastName, DateTimeInterval interval, ExaminationTypeDTO type) {
		super();
		this.id = id;
		this.dermFirstName = dermFirstName;
		this.dermLastName = dermLastName;
		this.interval = interval;
		this.type = type;
	}


	public ExaminationDTO(Examination exam) {
		this.id = exam.getId();
		this.dermFirstName = exam.getDermatologist().getFirstName();
		this.dermLastName = exam.getDermatologist().getLastName();
		this.interval = exam.getInterval();
		this.type = new ExaminationTypeDTO(exam.getExamType());
	}


	public DateTimeInterval getInterval() {
		return interval;
	}


	public void setInterval(DateTimeInterval interval) {
		this.interval = interval;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDermFirstName() {
		return dermFirstName;
	}


	public void setDermFirstName(String dermFirstName) {
		this.dermFirstName = dermFirstName;
	}


	public String getDermLastName() {
		return dermLastName;
	}


	public void setDermLastName(String dermLastName) {
		this.dermLastName = dermLastName;
	}



	public ExaminationTypeDTO getType() {
		return type;
	}


	public void setType(ExaminationTypeDTO type) {
		this.type = type;
	}
	
	
}
