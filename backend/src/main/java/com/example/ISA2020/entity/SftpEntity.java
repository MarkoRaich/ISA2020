package com.example.ISA2020.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class SftpEntity {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "NameOfFile cannot be null.")
    @Column(nullable = false)
	private String nameOfFile;
	
	
	public SftpEntity() {
		super();
	}


	public SftpEntity(Long id, @NotNull(message = "NameOfFile cannot be null.") String nameOfFile) {
		super();
		this.id = id;
		this.nameOfFile = nameOfFile;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNameOfFile() {
		return nameOfFile;
	}


	public void setNameOfFile(String nameOfFile) {
		this.nameOfFile = nameOfFile;
	}
	

	
}
