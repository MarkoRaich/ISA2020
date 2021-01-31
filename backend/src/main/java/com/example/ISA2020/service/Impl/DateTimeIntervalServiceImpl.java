package com.example.ISA2020.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.repository.DateTimeIntervalRepository;
import com.example.ISA2020.service.DateTimeIntervalService;

@Service
public class DateTimeIntervalServiceImpl implements DateTimeIntervalService {
	
	@Autowired
	private DateTimeIntervalRepository dateTimeRepository;
	
	@Override
	public DateTimeInterval findById(Long id) {
		// TODO Auto-generated method stub
		return dateTimeRepository.findOneById(id);
	}

	@Override
	public List<DateTimeInterval> getAllDateTimeIntervals() {
		// TODO Auto-generated method stub
		return dateTimeRepository.findAll();
	}

}
