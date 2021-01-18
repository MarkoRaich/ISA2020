package com.example.ISA2020.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="pricelist")
@Entity
public class PricelistDrug {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime endDateTime;
    
    @OneToMany(mappedBy = "pricelistDrug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacy> pharmacies = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "pricelist_drug_drug",
            joinColumns = @JoinColumn(name = "pricelist_drug_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id") )
    private Set<Drug> drugs;

    
    //KONSTRUKTORI
	public PricelistDrug(LocalDateTime startDateTime, LocalDateTime endDateTime, Set<Pharmacy> pharmacies) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.pharmacies = pharmacies;
		this.drugs = null;
	}

	
	//GETERI I SETERI
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


	public Set<Pharmacy> getPharmacies() {
		return pharmacies;
	}


	public void setPharmacies(Set<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}


	public Set<Drug> getDrugs() {
		return drugs;
	}


	public void setDrugs(Set<Drug> drugs) {
		this.drugs = drugs;
	}
    
    
    
    
    
}
