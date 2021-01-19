package com.example.ISA2020.entity;

import javax.persistence.*;

public class PricelistItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	private Drug drug;
	
	@Column
	private double price;
}
