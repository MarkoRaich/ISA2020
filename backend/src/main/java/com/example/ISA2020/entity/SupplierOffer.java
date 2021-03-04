package com.example.ISA2020.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.example.ISA2020.entity.users.Supplier;
import com.example.ISA2020.enumeration.SupplierOfferStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SupplierOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private double offer;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime deliveryDeadline;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private Supplier supplier;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
	private PurchaseOrder purchaseOrder;
	
	@Enumerated(EnumType.STRING)
	@Column()
	private SupplierOfferStatus status;

	
	
	
	public SupplierOffer() {
		super();
	}




	public SupplierOffer(Long id, double offer, LocalDateTime deliveryDeadline, Supplier supplier,
			PurchaseOrder purchaseOrder, SupplierOfferStatus status) {
		super();
		this.id = id;
		this.offer = offer;
		this.deliveryDeadline = deliveryDeadline;
		this.supplier = supplier;
		this.purchaseOrder = purchaseOrder;
		this.status = status;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public double getOffer() {
		return offer;
	}




	public void setOffer(double offer) {
		this.offer = offer;
	}




	public LocalDateTime getDeliveryDeadline() {
		return deliveryDeadline;
	}




	public void setDeliveryDeadline(LocalDateTime deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}




	public Supplier getSupplier() {
		return supplier;
	}




	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}




	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}




	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}




	public SupplierOfferStatus getStatus() {
		return status;
	}




	public void setStatus(SupplierOfferStatus status) {
		this.status = status;
	}
	
	
	
	
}



