package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;

import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.Promotion;

public class PromotionDTO {
	
	private Long id;
	
	private String content;
	
	@NotEmpty(message = "Start date and time is empty.")
    private String startDateTime;

    @NotEmpty(message = "End date and time is empty.")
    private String endDateTime;
	
	private PharmacyDTO pharmacyDTO;
	
	
	
	
	
	public PromotionDTO() {
		super();
	}





	public PromotionDTO(Long id, String content, String startDateTime, String endDateTime, PharmacyDTO pharmacyDTO) {
		super();
		this.id = id;
		this.content = content;
		this.startDateTime=startDateTime;
		this.endDateTime=endDateTime;
		this.pharmacyDTO = pharmacyDTO;
	}

	public PromotionDTO(Promotion promotion) {
		this.id=promotion.getId();
		this.content=promotion.getContent();
		this.startDateTime=promotion.getPeriod().getStartDateTime().toString();
		this.endDateTime=promotion.getPeriod().getEndDateTime().toString();
		this.pharmacyDTO=new PharmacyDTO(promotion.getPharmacy());
	}



	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
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





	public PharmacyDTO getPharmacyDTO() {
		return pharmacyDTO;
	}





	public void setPharmacyDTO(PharmacyDTO pharmacyDTO) {
		this.pharmacyDTO = pharmacyDTO;
	}

	
	
	
	

}
