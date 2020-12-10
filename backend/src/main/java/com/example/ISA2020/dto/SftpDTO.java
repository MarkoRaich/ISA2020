package com.example.ISA2020.dto;

import com.example.ISA2020.entity.SftpEntity;

public class SftpDTO {
	
	private Long id;
	
	private String nameOfFile;

	public SftpDTO() {
		super();
	}

	public SftpDTO(Long id, String nameOfFile) {
		super();
		this.id = id;
		this.nameOfFile = nameOfFile;
	}
	
	public SftpDTO(SftpDTO dto ) {
		this(dto.getId(), dto.getNameOfFile());
	}
	
	public SftpDTO(SftpEntity entity) {
		this(entity.getId(), entity.getNameOfFile());
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
