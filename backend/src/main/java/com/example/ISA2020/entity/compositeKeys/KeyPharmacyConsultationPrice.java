package com.example.ISA2020.entity.compositeKeys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class KeyPharmacyConsultationPrice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "pharmacy_id")
    Long pharmacyId;

    @Column(name = "consultation_id")
    Long consultationId;




    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consultationId == null) ? 0 : consultationId.hashCode());
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
		KeyPharmacyConsultationPrice other = (KeyPharmacyConsultationPrice) obj;
		if (consultationId == null) {
			if (other.consultationId != null)
				return false;
		} else if (!consultationId.equals(other.consultationId))
			return false;
		if (pharmacyId == null) {
			if (other.pharmacyId != null)
				return false;
		} else if (!pharmacyId.equals(other.pharmacyId))
			return false;
		return true;
	}

  
}
