package com.example.ISA2020.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.compositeKeys.KeyDermatologistPharmacyWorkHours;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.EntityStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DermatologistWorkHours {
	
	@EmbeddedId
	private KeyDermatologistPharmacyWorkHours id;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@MapsId("dermatologistId")
	@JoinColumn(name = "dermatologist_id", referencedColumnName = "id")
	private Dermatologist dermatologist;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	@MapsId("pharmacyId")
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;
	
	

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
	
	

	
	
	public DermatologistWorkHours() {}
	
	


	public DermatologistWorkHours( KeyDermatologistPharmacyWorkHours id, @NotNull LocalTime timeFrom, @NotNull LocalTime timeTo,
			EntityStatus status, Dermatologist dermatologist, Pharmacy pharmacy) {
		super();
		this.id=id;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.status = status;
		this.dermatologist = dermatologist;
		this.pharmacy = pharmacy;
	}




	public KeyDermatologistPharmacyWorkHours getId() {
		return id;
	}


	public void setId(KeyDermatologistPharmacyWorkHours id) {
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



	//metoda za poveru da li je termin slobodan
	public boolean isAvailable(LocalTime startExaminationTime, LocalTime endExaminationTime) {
		
		if(( startExaminationTime.isAfter(timeFrom) || startExaminationTime.equals(timeFrom)) && startExaminationTime.isBefore(timeTo)) { //pocinje tacno ili posle pocetka RV i pre kraja
			if(endExaminationTime.isAfter(timeFrom) && (endExaminationTime.isBefore(timeTo) || endExaminationTime.equals(timeTo))) { //zavrsava posle pocetka i pre ili tacno na kraj RV
				return true;
			}
		}
		return false;
	}
	
	
	//za ubacivanje dermatologa u apoteku da radi 
	public boolean hasNoColision(LocalTime workHoursFrom, LocalTime workHoursTo) {
		
		if( workHoursFrom.isBefore(timeFrom) && workHoursTo.isBefore(timeFrom) || workHoursTo.equals(timeFrom)) { return true; }
		
		else if(workHoursFrom.isAfter(timeTo) || workHoursFrom.equals(timeTo) && workHoursTo.isAfter(timeTo)) { return true; }
		
		return false;
	}


	@Override
	public String toString() {
		return "DermWorkHours [id=" + id + ", timeFrom=" + timeFrom + ", timeTo=" + timeTo + ", status=" + status
				+ ", dermatologist=" + dermatologist + ", pharmacy=" + pharmacy + "]";
	}





	
	
}
