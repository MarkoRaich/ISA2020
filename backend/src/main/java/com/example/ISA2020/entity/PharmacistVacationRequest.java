package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.VacationRequestStatus;

@Table(name="pharmVacationReq")
@Entity
public class PharmacistVacationRequest { //odobrava administrator sistema

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated
	private VacationRequestStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pharmacist pharmacist;
}
