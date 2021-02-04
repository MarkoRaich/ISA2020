package com.example.ISA2020.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DermPharmWorkKey implements Serializable {
	
	@Column(name = "pharmacy_id")
	Long pharmacyId;
	
	@Column(name = "dermatologist_id")
	Long dermatologistId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dermatologistId == null) ? 0 : dermatologistId.hashCode());
		result = prime * result + ((pharmacyId == null) ? 0 : pharmacyId.hashCode());
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
		DermPharmWorkKey other = (DermPharmWorkKey) obj;
		if (dermatologistId == null) {
			if (other.dermatologistId != null)
				return false;
		} else if (!dermatologistId.equals(other.dermatologistId))
			return false;
		if (pharmacyId == null) {
			if (other.pharmacyId != null)
				return false;
		} else if (!pharmacyId.equals(other.pharmacyId))
			return false;
		return true;
	}
	
	

}
