package com.example.ISA2020.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.PurchaseOrderDTO;
import com.example.ISA2020.dto.PurchaseOrderItemDTO;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.PurchaseOrder;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.entity.users.Supplier;
import com.example.ISA2020.entity.PurchaseOrderItem;
import com.example.ISA2020.entity.SupplierOffer;
import com.example.ISA2020.entity.compositeKeys.KeyDrugPharmacyQuantity;
import com.example.ISA2020.enumeration.EntityStatus;
import com.example.ISA2020.enumeration.PurchaseOrderStatus;
import com.example.ISA2020.enumeration.SupplierOfferStatus;
import com.example.ISA2020.repository.PurchaseOrderRepository;
import com.example.ISA2020.service.DrugQuantityService;
import com.example.ISA2020.service.DrugService;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService  {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private DrugService drugService;
	
	@Autowired
	private DrugQuantityService drugQuantityService;
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	
	@Override
	public List<PurchaseOrderDTO> getPurchaseOrdersInPharmacy(Pharmacy pharmacy) {
				
		return convertToDTO(purchaseOrderRepository.findByPharmacyIdAndStatusNot(pharmacy.getId(), PurchaseOrderStatus.DELETED ));
		
	}
	
	@Override
	public PurchaseOrder findOneById(Long id) {
		return purchaseOrderRepository.findOneById(id);
	}
	
	@Override
	public PurchaseOrderDTO createPurchaseOrder(@Valid PurchaseOrderDTO purchaseOrderDTO, PharmacyAdmin pharmacyAdmin) {
		
		
		PurchaseOrder order = new PurchaseOrder();
		
		order.setStatus(PurchaseOrderStatus.valueOf(purchaseOrderDTO.getStatus()));
		order.setPharmacy(pharmacyAdmin.getPharmacy());
		order.setDeadline(convertToDT(purchaseOrderDTO.getDeadline()));								
		order.setOrderItems(convertFromDTO(purchaseOrderDTO.getOrderitems(), order));
		order.setPharmacyAdmin(pharmacyAdmin);
			
		
		Set<DrugQuantity> drugs = pharmacyAdmin.getPharmacy().getDrugsWithQunatity();
		
		for(PurchaseOrderItem item: order.getOrderItems()) {
			boolean isInPharmacy = false;
			boolean isInPharmacyButDeleted=false;
			for(DrugQuantity drug: drugs ) {
				if(item.getDrug().getId() == drug.getDrug().getId()) {
					isInPharmacy = true;
					if(drug.getStatus()==EntityStatus.DELETED) {
						isInPharmacyButDeleted=true;
					}
				}
			}					//dodavanje leka u ponnudu apoteke sa kolicinom nula!
			if(!isInPharmacy) {													
				drugs.add(new DrugQuantity( new KeyDrugPharmacyQuantity(pharmacyAdmin.getPharmacy().getId(), item.getDrug().getId()),
											pharmacyAdmin.getPharmacy(),
											item.getDrug(),
											0,
											EntityStatus.ACTIVE));
			} 
			else if(isInPharmacy && isInPharmacyButDeleted) {
				DrugQuantity drugQ = drugQuantityService.findById(new KeyDrugPharmacyQuantity(pharmacyAdmin.getPharmacy().getId(), item.getDrug().getId()));
				drugQ.setQuantity(0);
				drugQ.setStatus(EntityStatus.ACTIVE);
			}
		}
		
		
		PurchaseOrderDTO temp = new PurchaseOrderDTO(purchaseOrderRepository.save(order));

//		System.out.println("Narudzbenica nakon sto se sacuva: " + temp);
//		for(PurchaseOrderItemDTO item : temp.getOrderitems()) {
//			System.out.println(item.getDrug().getName() + " - " + item.getQuantity());
//			
//		}
		
		
		return temp;
	}
	
	@Override
	public PurchaseOrderDTO deletePurchaseOrder(Long pharmId, Long orderId) {
		
		PurchaseOrder order =  this.purchaseOrderRepository.findOneById(orderId);
		if(order == null) {
			return null;
		}
		
		if(order.getOffers().size() != 0 || order.getStatus() == PurchaseOrderStatus.FINISHED) {
			return null;
		}
		
		
		order.setStatus(PurchaseOrderStatus.DELETED);
		
		return new PurchaseOrderDTO(this.purchaseOrderRepository.save(order));
	}

	
	@Override
	public PurchaseOrderDTO acceptOffer(PurchaseOrder order, Long offerId) {
		
		boolean isWinner = false;
		
		//prolazak kroz ponude 
		for(SupplierOffer offer: order.getOffers()) {
			if(offer.getId() == offerId) {		//pobednicka ponuda, postavljanje statusa na prihvaceno i slanje mejla dobavljacu
				
				isWinner=true;
				offer.setStatus(SupplierOfferStatus.ACCEPTED);				
				System.out.println("Slanje emaila pobedniku dobavljacu: " + offer.getSupplier().getFirstName() + offer.getSupplier().getLastName());
				sendEmailSupplier(order, offer.getSupplier().getUsername(), isWinner);
				
			} else { 							//gubitnicke ponude, postavljanje statusa na odbijeno i slanje mejla dobavljacu
				
				isWinner=false;
				offer.setStatus(SupplierOfferStatus.DENIED);
				System.out.println("Slanje emaila gubitniku dobavljacu: " + offer.getSupplier().getFirstName() + offer.getSupplier().getLastName());
				sendEmailSupplier(order, offer.getSupplier().getUsername(), isWinner);
			}
		}
		
		order.setStatus(PurchaseOrderStatus.FINISHED);
		
		//azuriranje kolicine lekova u apoteci!
		Set<DrugQuantity> drugsQ =  order.getPharmacy().getDrugsWithQunatity();
		for(DrugQuantity drugQ : drugsQ) {
			for(PurchaseOrderItem item: order.getOrderItems()) {
				if(drugQ.getDrug().getId() == item.getDrug().getId()) {
					int temp = drugQ.getQuantity();
					drugQ.setQuantity(temp + item.getQuantity());
				}
			}
		}
		
		return new PurchaseOrderDTO(purchaseOrderRepository.save(order));
		
	}
	
	@Async
	private void sendEmailSupplier(PurchaseOrder order, String email, boolean winner) {
		
		
		String subject = "Obavestenje o tenderu u apoteci: " + order.getPharmacy().getName();
		
		StringBuilder sb = new StringBuilder();
		if(winner) {
			sb.append("Postovani, Vaša ponuda je prihvaćena. \n");
			sb.append("Kontaktirajte nas oko detalja uplate, hvala \n");
		} else {
			sb.append("Postovani, Vaša ponuda je odbijena. \n");
		}
       
        sb.append(System.lineSeparator());
        String text = sb.toString();
		
        System.out.println(text);
        
		emailNotificationService.sendEmail(email, subject, text);
		
	}
	
	
	
	private LocalDateTime convertToDT(String str) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return LocalDateTime.parse(str, formatter);
}

	
	
	private Set<PurchaseOrderItem> convertFromDTO(Set<PurchaseOrderItemDTO> itemsDTO, PurchaseOrder order){
		 Set<PurchaseOrderItem> returnItems = new HashSet<PurchaseOrderItem>();
		for(PurchaseOrderItemDTO itemDTO: itemsDTO) {
			returnItems.add( new PurchaseOrderItem(itemDTO.getQuantity(), drugService.findById(itemDTO.getDrug().getId()), order ));
		 }
		return returnItems;
	}
	
	
	private List<PurchaseOrderDTO> convertToDTO(List<PurchaseOrder> orders){
		
		List<PurchaseOrderDTO> ordersDTO = new ArrayList<>();
		for(PurchaseOrder order: orders) {
			ordersDTO.add(new PurchaseOrderDTO(order));
		}
		
		return ordersDTO;
	}

	

	
	
}
