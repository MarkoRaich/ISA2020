package com.example.ISA2020.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.ISA2020.enumeration.PurchaseOrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column()
	private PurchaseOrderStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
	private Pharmacy pharmacy;

	@OneToMany
	private Set<DrugQuantity> drugQuantityList = new HashSet<>();
	
	@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SupplierOffer> offers = new HashSet<SupplierOffer>();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime deadline;
}
