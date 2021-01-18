package com.example.ISA2020.entity;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.TimeOFFStatus;

public class TimeOFFDermatologist { //odobrava admin apoteke
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated
	private TimeOFFStatus status;
	
	@ManyToOne
	private Dermatologist dermatologist;
}
