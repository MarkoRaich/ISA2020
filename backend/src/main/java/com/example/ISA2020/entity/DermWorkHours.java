package com.example.ISA2020.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.users.Dermatologist;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DermWorkHours {
	
	@EmbeddedId
	private DermPharmWorkKey id;

	@JsonFormat(pattern = "HH:mm")   
	@NotNull
	@Column(nullable = false)
	private LocalTime timeFrom;
	
	@JsonFormat(pattern = "HH:mm")
	@NotNull
	@Column(nullable = false)
	private LocalTime timeTo;
	
	
	@ManyToOne
	@MapsId("dermatologistId")
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
	
	@ManyToOne
	@MapsId("pharmacyId")
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;
	
}
