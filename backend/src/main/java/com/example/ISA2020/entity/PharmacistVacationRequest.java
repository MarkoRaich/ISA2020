package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.VacationRequestStatus;

public class PharmacistVacationRequest { //odobrava administrator sistema

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated
	private VacationRequestStatus status;
	
	@ManyToOne
	private Dermatologist pharmacist;
}
