package com.example.ISA2020.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.ISA2020.enumeration.SupplierOfferStatus;

@Entity
public class SupplierOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "purchaseorder_id", referencedColumnName = "id")
	private PurchaseOrder purchaseOrder;
	
	@OneToMany
	private Set<DrugPrice> drugPrices = new HashSet<>();
	
	@Enumerated(EnumType.STRING)
	@Column()
	private SupplierOfferStatus status;
}



