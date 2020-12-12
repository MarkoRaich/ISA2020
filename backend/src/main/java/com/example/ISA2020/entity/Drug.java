package com.example.ISA2020.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Drug {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null.")
    @Column(nullable = false)
    private String name;
    
	
 	@ManyToMany(mappedBy = "drugs") 
 	private Set<Pharmacy> pharmacies;
	 

    @OneToMany(mappedBy = "drug")
    private Set<PharmacyDrugDetails> details;
    
	public Drug(@NotNull(message = "Name cannot be null.") String name) {
		super();
		this.name = name;
//		this.pharmacies = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
  
}
