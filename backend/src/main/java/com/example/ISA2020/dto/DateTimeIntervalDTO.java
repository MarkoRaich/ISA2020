package com.example.ISA2020.dto;

import java.time.LocalDateTime;

import com.example.ISA2020.entity.DateTimeInterval;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DateTimeIntervalDTO {
	 
		 private Long id;
		
		 @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		 private LocalDateTime startDateTime;
		 
		 @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		 private LocalDateTime endDateTime;

	 
	 
	 
	 
	public DateTimeIntervalDTO() {
		super();
	}

	public DateTimeIntervalDTO(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		super();
		this.id = id;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	
	public DateTimeIntervalDTO(DateTimeInterval interval) {
		super();
		this.id=interval.getId();
		this.startDateTime=interval.getStartDateTime();
		this.endDateTime = interval.getEndDateTime();
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
