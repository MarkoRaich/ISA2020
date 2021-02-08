package com.example.ISA2020.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.example.ISA2020.enumeration.EntityStatus;
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
	
	@Enumerated(EnumType.STRING)
	@Column()
	private EntityStatus status;
	
	
	@ManyToOne
	@MapsId("dermatologistId")
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
	
	@ManyToOne
	@MapsId("pharmacyId")
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;
	
	
	public DermWorkHours() {}
	
	


	public DermWorkHours(DermPharmWorkKey id, @NotNull LocalTime timeFrom, @NotNull LocalTime timeTo,
			EntityStatus status, Dermatologist dermatologist, Pharmacy pharmacy) {
		super();
		this.id = id;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.status = status;
		this.dermatologist = dermatologist;
		this.pharmacy = pharmacy;
	}




	public DermPharmWorkKey getId() {
		return id;
	}


	public void setId(DermPharmWorkKey id) {
		this.id = id;
	}


	public LocalTime getTimeFrom() {
		return timeFrom;
	}


	public void setTimeFrom(LocalTime timeFrom) {
		this.timeFrom = timeFrom;
	}


	public LocalTime getTimeTo() {
		return timeTo;
	}


	public void setTimeTo(LocalTime timeTo) {
		this.timeTo = timeTo;
	}


	public EntityStatus getStatus() {
		return status;
	}


	public void setStatus(EntityStatus status) {
		this.status = status;
	}


	public Dermatologist getDermatologist() {
		return dermatologist;
	}


	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}


	public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	
}
