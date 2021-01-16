package com.example.ISA2020.dto;

import java.time.LocalDateTime;


public class PricelistDTO {
	
    private Long id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    
    
	public PricelistDTO() {
		super();
	}


	public PricelistDTO(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		super();
		this.id = id;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	
	public PricelistDTO(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}


	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}


	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}


	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	
    
    
}
