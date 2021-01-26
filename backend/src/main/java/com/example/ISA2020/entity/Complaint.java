package com.example.ISA2020.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.users.Patient;

//ZALBA
@Table(name="complaint")
@Entity
public class Complaint {
	
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

    

    @ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    
    //@OneToOne(mappedBy = "complaint")
    //private Examination examination; //Examination ce biti povezan sa Farmaceutom ili Dermatologom
    
    
    //KONSTRUKTORI
    public Complaint() {
		super();
	}
       
	public Complaint(@NotNull(message = "Subject cannot be null.") String subject,
			@NotNull(message = "Message cannot be null.") String message, Patient patient
			) {
		super();
		this.subject = subject;
		this.message = message;
		this.patient = patient;
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
	
	
    
}
