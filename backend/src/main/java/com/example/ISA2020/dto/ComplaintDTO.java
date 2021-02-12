package com.example.ISA2020.dto;

public class ComplaintDTO {
	private String subject;
	
	private String message;
	
	private String doctorName;
	
	private String examination;

	public ComplaintDTO(String subject, String message, String doctorName, String examination) {
		super();
		this.subject = subject;
		this.message = message;
		this.doctorName = doctorName;
		this.examination = examination;
	}

	public ComplaintDTO() {
		super();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}
	
	
}
