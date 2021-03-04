package com.example.ISA2020.service;

import java.util.List;

import javax.validation.Valid;

import com.example.ISA2020.dto.PurchaseOrderDTO;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.PurchaseOrder;
import com.example.ISA2020.entity.users.PharmacyAdmin;

public interface PurchaseOrderService {

	List<PurchaseOrderDTO> getPurchaseOrdersInPharmacy(Pharmacy pharmacy);

	PurchaseOrderDTO createPurchaseOrder(@Valid PurchaseOrderDTO purchaseOrderDTO, PharmacyAdmin pharmacyAdmin);

	PurchaseOrder findOneById(Long id);

	PurchaseOrderDTO acceptOffer(PurchaseOrder order, Long idOffer);

	PurchaseOrderDTO deletePurchaseOrder(Long pharmId, Long orderId);

}
