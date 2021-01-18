package com.example.ISA2020.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//Recept koji se izdaje nakon pregleda/savetovanja
@Table(name="prescription")
@Entity
public class Prescription {
	
	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(mappedBy = "prescription")
    private Examination examination;
}
