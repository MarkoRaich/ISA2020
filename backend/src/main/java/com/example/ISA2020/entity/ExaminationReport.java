package com.example.ISA2020.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//izvestaj o pregledu
@Table(name="examinationReport")
@Entity
public class ExaminationReport {
	
	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull(message = "Subject cannot be null.")
    @Column(nullable = false)
    private String subject;
    
    @NotNull(message = "Message cannot be null.")
    @Column(nullable = false)
    private String message;
	
	@OneToOne(mappedBy = "examinationReport")
    private Examination examination;

    @OneToOne(cascade = CascadeType.ALL)
    private Prescription prescription;

    
    //KONSTRUKTORI
	public ExaminationReport() {
		super();
	}

	public ExaminationReport(@NotNull(message = "Subject cannot be null.") String subject,
			@NotNull(message = "Message cannot be null.") String message, Examination examination,
			Prescription prescription) {
		super();
		this.subject = subject;
		this.message = message;
		this.examination = examination;
		this.prescription = prescription;
	}

	//GETERI I SETERI
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
    
    
    
}
