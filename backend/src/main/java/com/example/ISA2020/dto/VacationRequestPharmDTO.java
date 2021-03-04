package com.example.ISA2020.dto;

import com.example.ISA2020.entity.VacationRequestPharm;

public class VacationRequestPharmDTO {

	private long id;
	
	private DateTimeIntervalDTO interval;
	
	private String status;
	
	private PharmacistDTO pharmacist;
	
	
	
	
	public VacationRequestPharmDTO() {}
	
	public VacationRequestPharmDTO(long id, DateTimeIntervalDTO interval, String status, PharmacistDTO pharmacist) {
		super();
		this.id = id;
		this.interval = interval;
		this.status = status;
		this.pharmacist = pharmacist;
	}

	public VacationRequestPharmDTO(VacationRequestPharm request) {
		super();
		this.id = request.getId();
		this.interval = new DateTimeIntervalDTO(request.getInterval());
		this.status = request.getStatus().toString();
		this.pharmacist = new PharmacistDTO(request.getPharmacist());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DateTimeIntervalDTO getInterval() {
		return interval;
	}

	public void setInterval(DateTimeIntervalDTO interval) {
		this.interval = interval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PharmacistDTO getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(PharmacistDTO pharmacist) {
		this.pharmacist = pharmacist;
	}
	
	
}
