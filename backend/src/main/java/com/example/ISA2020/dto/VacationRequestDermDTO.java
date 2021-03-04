package com.example.ISA2020.dto;

import com.example.ISA2020.entity.VacationRequestDerm;

public class VacationRequestDermDTO {

	private long id;
	
	private DateTimeIntervalDTO interval;
	
	private String status;
	
	private DermatologistDTO dermatologist;
	
	
	
	
	public VacationRequestDermDTO() {}
	
	public VacationRequestDermDTO(long id, DateTimeIntervalDTO interval, String status, DermatologistDTO dermatologist) {
		super();
		this.id = id;
		this.interval = interval;
		this.status = status;
		this.dermatologist = dermatologist;
	}

	public VacationRequestDermDTO(VacationRequestDerm request) {
		super();
		this.id = request.getId();
		this.interval = new DateTimeIntervalDTO(request.getInterval());
		this.status = request.getStatus().toString();
		this.dermatologist = new DermatologistDTO(request.getDermatologist());
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

	public DermatologistDTO getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(DermatologistDTO dermatologist) {
		this.dermatologist = dermatologist;
	}

	
	
	
}
