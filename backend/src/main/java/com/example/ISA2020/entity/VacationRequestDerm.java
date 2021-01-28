package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.VacationRequestStatus;


@Entity
public class VacationRequestDerm { //odobrava admin apoteke
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DateTimeInterval interval;

	@Enumerated
	private VacationRequestStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
}
