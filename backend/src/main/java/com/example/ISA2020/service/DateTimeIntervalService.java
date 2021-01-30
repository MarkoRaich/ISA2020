package com.example.ISA2020.service;

import java.util.List;

import com.example.ISA2020.entity.DateTimeInterval;

public interface DateTimeIntervalService {
	
	DateTimeInterval findById(Long id);
	
	List<DateTimeInterval> getAllDateTimeIntervals();
}
