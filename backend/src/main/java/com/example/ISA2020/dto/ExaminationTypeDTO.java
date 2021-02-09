package com.example.ISA2020.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.example.ISA2020.entity.ExaminationType;

public class ExaminationTypeDTO {

	 @NotNull(message = "Id is null.")
	    private Long id;
	 
	 	@NotNull(message = "Name cannot be null.")
	 	 @Size(message = "Max size for label is 30.", max = 30)
	    private String name;
	    
	    @NotNull(message = "Description cannot be null.")
	    @Size(message = "Max size for label is 30.", max = 30)
	    private String description;

	    @NotNull(message = "Price is null.")
	    @Positive(message = "Price is not a positive number.")
	    private Double price;

	    public ExaminationTypeDTO() {}

	    
	    
		public ExaminationTypeDTO(@NotNull(message = "Id is null.") Long id,
				@NotNull(message = "Name cannot be null.") @Size(message = "Max size for label is 30.", max = 30) String name,
				@NotNull(message = "Description cannot be null.") @Size(message = "Max size for label is 30.", max = 30) String description,
				@NotNull(message = "Price is null.") @Positive(message = "Price is not a positive number.") Double price) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
		}



		public ExaminationTypeDTO(ExaminationType examType) {
			this.id=examType.getId();
			this.name=examType.getName();
			this.description=examType.getDescription();
			this.price=examType.getPrice();
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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}
	    
	    
}
