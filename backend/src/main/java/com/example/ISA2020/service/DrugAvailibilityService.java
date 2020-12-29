package com.example.ISA2020.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.ISA2020.dto.DrugSearchDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.PharmacyDrugDetails;
import com.example.protos.Drug;
import com.example.protos.DrugAvailabilityGrpc.DrugAvailabilityImplBase;
import com.example.protos.FindDrugRequest;
import com.example.protos.FindDrugResponse;
import com.example.protos.OrderDrugRequest;
import com.example.protos.OrderDrugResponse;
import com.example.protos.Pharmacy;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class DrugAvailibilityService extends DrugAvailabilityImplBase {
	
	@Autowired
	private PharmacyDrugDetailsService pharmacyDrugDetailsService;

	@Override
	public void findDrug(FindDrugRequest request, StreamObserver<FindDrugResponse> responseObserver) {
		
		List<PharmacyDrugDetails> pharmacyDrugDetails = pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		
		List<Drug> ret = new ArrayList<>();
		
		for(PharmacyDrugDetails p : pharmacyDrugDetails) {
			if(p.getDrug().getName().toLowerCase().contains(request.getName().toLowerCase())) {
				Drug dto = Drug.newBuilder().setId(Integer.parseInt(p.getDrug().getId().toString()))
											.setName(p.getDrug().getName())
											.setCode(p.getDrug().getCode())
											.setQuantity(p.getQuantity())
											.setPharmacy(Pharmacy.newBuilder().setId(Integer.parseInt(p.getPharmacy().getId().toString())).setName(p.getPharmacy().getName()).setAddress(p.getPharmacy().getAddress())).build();
				ret.add(dto);
			}
		}
		
		
		FindDrugResponse response = FindDrugResponse.newBuilder().addAllDrugs(ret).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	@Override
	public void orderDrug(OrderDrugRequest request, StreamObserver<OrderDrugResponse> responseObserver) {
		List<PharmacyDrugDetails> pharmacyDrugDetails = pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		
		int oldQuantity = 0;
		int newQuantity = 0;
		int q = request.getQuantity();
		OrderDrugResponse response = null;
		
		for(PharmacyDrugDetails p : pharmacyDrugDetails) {
			if(p.getPharmacy().getId() == request.getIdPharmacy()) {
				//System.out.println("1");
				if(p.getDrug().getId() == request.getIdDrug()) {
					//System.out.println("2");
					if(p.getQuantity() >= q) {
						//System.out.println("3");
						oldQuantity = p.getQuantity();
						newQuantity = oldQuantity - q;
						p.setQuantity(newQuantity);
						pharmacyDrugDetailsService.save(p);
						response = OrderDrugResponse.newBuilder().setSuccess(true).build();
					}else {
						response = OrderDrugResponse.newBuilder().setSuccess(false).build();
					}
				}
			}
		}
		
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
