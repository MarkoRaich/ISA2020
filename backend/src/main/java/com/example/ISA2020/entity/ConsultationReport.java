package com.example.ISA2020.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name="consultationReport")
@Entity
public class ConsultationReport {

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

    @OneToOne(mappedBy = "consultationReport")
    private Consultation consultation;

    @OneToOne(cascade = CascadeType.ALL)
    private Prescription prescription;
    

    //KONSTRUKTORI
	public ConsultationReport() {
		super();
	}


	public ConsultationReport(@NotNull(message = "Subject cannot be null.") String subject,
			@NotNull(message = "Message cannot be null.") String message, Consultation consultation,
			Prescription prescription) {
		super();
		this.subject = subject;
		this.message = message;
		this.consultation = consultation;
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


	public Consultation getConsultation() {
		return consultation;
	}


	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}


	public Prescription getPrescription() {
		return prescription;
	}


	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
	
    
    
    
}
