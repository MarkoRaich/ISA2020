package com.example.ISA2020.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;

import com.example.ISA2020.dto.PurchaseOrderDTO;
import com.example.ISA2020.dto.PurchaseOrderItemDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.enumeration.EntityStatus;
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
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_admin_id", referencedColumnName = "id")
	private PharmacyAdmin pharmacyAdmin;

	@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PurchaseOrderItem> orderItems = new HashSet<>();
	
	@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SupplierOffer> offers = new HashSet<SupplierOffer>();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime deadline;

	
	
	public PurchaseOrder() {
		super();
	}


	
	public PurchaseOrder(Long id, PurchaseOrderStatus status, Pharmacy pharmacy, PharmacyAdmin pharmacyAdmin,
			Set<PurchaseOrderItem> orderItems, Set<SupplierOffer> offers, LocalDateTime deadline) {
		super();
		this.id = id;
		this.status = status;
		this.pharmacy = pharmacy;
		this.pharmacyAdmin = pharmacyAdmin;
		this.orderItems = orderItems;
		this.offers = offers;
		this.deadline = deadline;
	}



	public PurchaseOrder(PurchaseOrderStatus status, Pharmacy pharmacy, Set<PurchaseOrderItem> orderItems,
			 LocalDateTime deadline) {
		super();
		this.status = status;
		this.pharmacy = pharmacy;
		this.orderItems = orderItems;
		this.deadline = deadline;
	}


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PurchaseOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseOrderStatus status) {
		this.status = status;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Set<PurchaseOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<PurchaseOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Set<SupplierOffer> getOffers() {
		return offers;
	}

	public void setOffers(Set<SupplierOffer> offers) {
		this.offers = offers;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public PharmacyAdmin getPharmacyAdmin() {
		return pharmacyAdmin;
	}

	public void setPharmacyAdmin(PharmacyAdmin pharmacyAdmin) {
		this.pharmacyAdmin = pharmacyAdmin;
	}
	
	
	
}
