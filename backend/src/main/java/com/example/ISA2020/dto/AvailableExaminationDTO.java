package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.users.Dermatologist;

public class AvailableExaminationDTO {
		
		private Long id;
	
		@NotEmpty(message = "Start date and time is empty.")
	    private String startDateTime;

	    @NotEmpty(message = "End date and time is empty.")
	    private String endDateTime;

	    @NotNull(message = "Examination type is null")
	    private ExaminationTypeDTO examinationTypeDTO;


	    @NotNull(message = "Doctor  is null")
	    private DermatologistDTO dermatologistDTO;

	    
	    

		public AvailableExaminationDTO() {
			super();
		}


		public AvailableExaminationDTO(Long id, Dermatologist derm, DateTimeInterval interval,
				ExaminationTypeDTO examinationTypeDTO) {
			this.id=id;
			this.dermatologistDTO=new DermatologistDTO(derm);
			this.examinationTypeDTO = examinationTypeDTO;
			this.startDateTime=interval.getStartDateTime().toString();
			this.endDateTime=interval.getEndDateTime().toString();
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getStartDateTime() {
			return startDateTime;
		}


		public void setStartDateTime(String startDateTime) {
			this.startDateTime = startDateTime;
		}


		public String getEndDateTime() {
			return endDateTime;
		}


		public void setEndDateTime(String endDateTime) {
			this.endDateTime = endDateTime;
		}


		public ExaminationTypeDTO getExaminationTypeDTO() {
			return examinationTypeDTO;
		}


		public void setExaminationTypeDTO(ExaminationTypeDTO examinationTypeDTO) {
			this.examinationTypeDTO = examinationTypeDTO;
		}


		public DermatologistDTO getDermatologistDTO() {
			return dermatologistDTO;
		}


		public void setDermatologistDTO(DermatologistDTO dermatologistDTO) {
			this.dermatologistDTO = dermatologistDTO;
		}
	    
	    
	    
	    
}
