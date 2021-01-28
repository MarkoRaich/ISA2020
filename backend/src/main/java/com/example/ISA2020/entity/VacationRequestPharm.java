package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.VacationRequestStatus;


@Entity
public class VacationRequestPharm { //odobrava administrator sistema

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

	@Enumerated
	private VacationRequestStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacist_id", referencedColumnName = "id")
	private Pharmacist pharmacist;
}
