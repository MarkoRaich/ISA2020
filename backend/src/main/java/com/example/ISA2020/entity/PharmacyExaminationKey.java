package com.example.ISA2020.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PharmacyExaminationKey implements Serializable {


	private static final long serialVersionUID = 1L;

	@Column(name = "pharmacy_id")
    Long pharmacyId;

    @Column(name = "examination_id")
    Long examinationId;




    @Override
   	public int hashCode() {
   		final int prime = 31;
   		int result = 1;
   		result = prime * result + ((pharmacyId == null) ? 0 : pharmacyId.hashCode());
   		result = prime * result + ((examinationId == null) ? 0 : examinationId.hashCode());
   		return result;
   	}

   	@Override
   	public boolean equals(Object obj) {
   		if (this == obj)
   			return true;
   		if (obj == null)
   			return false;
   		if (getClass() != obj.getClass())
   			return false;
   		PharmacyExaminationKey other = (PharmacyExaminationKey) obj;
   		if (pharmacyId == null) {
   			if (other.pharmacyId != null)
   				return false;
   		} else if (!pharmacyId.equals(other.pharmacyId))
   			return false;
   		if (examinationId == null) {
   			if (other.examinationId != null)
   				return false;
   		} else if (!examinationId.equals(other.examinationId))
   			return false;
   		return true;
   	}
}
