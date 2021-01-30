package com.example.ISA2020.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.enumeration.DrugType;
import com.fasterxml.jackson.annotation.JsonIgnore;


// PREPARAT - LEK
@Table(name="drug")
@Entity
public class Drug {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null.")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Code cannot be null.")
    @Column(nullable = false)
    private String code;
    
    @Enumerated(EnumType.STRING)
    @Column()
    private DrugType type;
    
    @OneToMany(mappedBy = "drug", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();
    
    //DRUG SPECS 3.42 ima jos atributa mozda u posebnu klasu!?  
   
    
    
	public Drug() {
		super();
	}

	public Drug(String name,  String code) {
		super();
		this.name = name;
		this.code = code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	
	
	
	
  
}
