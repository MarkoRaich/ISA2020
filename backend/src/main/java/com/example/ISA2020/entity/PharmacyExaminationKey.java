package com.example.ISA2020.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PharmacyExaminationKey implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "pharmacy_id")
    Long pharmacyId;

    @Column(name = "pricelistExamination_id")
    Long pricelistExaminationId;
}
