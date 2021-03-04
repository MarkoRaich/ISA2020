package com.example.ISA2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISA2020.entity.PurchaseOrder;
import com.example.ISA2020.enumeration.PurchaseOrderStatus;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

	List<PurchaseOrder> findByPharmacyId(Long pharmId);

	PurchaseOrder findOneById(Long id);

	List<PurchaseOrder> findByPharmacyIdAndStatusNot(Long id, PurchaseOrderStatus deleted);

}
