package com.example.ISA2020.entity.grades;

import javax.persistence.*;

import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.Patient;

@Entity
public class GradePharmacy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn
    private Pharmacy pharmacy;

    @ManyToOne
    @JoinColumn
    private Patient patient;

    @Column
    private int grade;
    
    public GradePharmacy() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
    
    
    
}
