package com.example.ISA2020.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class SupplierOffer {

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "purchaseorder_id", referencedColumnName = "id")
	private PurchaseOrder purchaseOrder;
	
	@OneToMany
	private Set<DrugPrice> drugPrices = new HashSet<>();
	
}



