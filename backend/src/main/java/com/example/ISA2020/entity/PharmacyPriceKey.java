package com.example.ISA2020.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PharmacyPriceKey implements Serializable {

    @Column(name = "pharmacy_id")
    Long pharmacyId;

    @Column(name = "drug_id")
    Long drugId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drugId == null) ? 0 : drugId.hashCode());
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
		PharmacyPriceKey other = (PharmacyPriceKey) obj;
		if (drugId == null) {
			if (other.drugId != null)
				return false;
		} else if (!drugId.equals(other.drugId))
			return false;
		if (pharmacyId == null) {
			if (other.pharmacyId != null)
				return false;
		} else if (!pharmacyId.equals(other.pharmacyId))
			return false;
		return true;
	}

    // standard constructors, getters, and setters
    // hashcode and equals implementation
    
    
}
