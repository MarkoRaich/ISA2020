package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.VacationRequestStatus;

@Table(name="dermVacationReq")
@Entity
public class DermatologistVacationRequest { //odobrava admin apoteke
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated
	private VacationRequestStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Dermatologist dermatologist;
}
