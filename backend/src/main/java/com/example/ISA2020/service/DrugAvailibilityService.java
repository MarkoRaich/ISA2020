package com.example.ISA2020.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ISA2020.entity.PharmacyDrugDetails;
import com.example.protos.Drug;
import com.example.protos.DrugAvailabilityGrpc.DrugAvailabilityImplBase;
import com.example.protos.FindDrugRequest;
import com.example.protos.FindDrugResponse;
import com.example.protos.Pharmacy;

import io.grpc.stub.StreamObserver;

@Repository
public class DrugAvailibilityService extends DrugAvailabilityImplBase {

	@Autowired
	private PharmacyDrugDetailsService pharmacyDrugDetailsService;

	@Override
	public void findDrug(FindDrugRequest request, StreamObserver<FindDrugResponse> responseObserver) {
		ArrayList<Drug> ret = new ArrayList<Drug>();

		/*
		 * List<PharmacyDrugDetails> pharmacyDrugDetails =
		 * pharmacyDrugDetailsService.getAllPharmacyDrugDetails();
		 * 
		 * 
		 * for(PharmacyDrugDetails p : pharmacyDrugDetails) {
		 * if(p.getDrug().getName().toLowerCase().contains(request.getName().toLowerCase
		 * ())) { Pharmacy ph1 =
		 * Pharmacy.newBuilder().setId(Integer.parseInt(p.getPharmacy().getId().toString
		 * ())) .setName(p.getPharmacy().getName()).build();
		 * 
		 * Drug d1 =
		 * Drug.newBuilder().setId(Integer.parseInt(p.getDrug().getId().toString()))
		 * .setName(p.getDrug().getName()).setQuantity(p.getQuantity()).setPharmacy(ph1)
		 * .build();
		 * 
		 * ret.add(d1); } }
		 */

		List<Drug> drugs = new ArrayList<>();
		
		Pharmacy ph1 = Pharmacy.newBuilder().setId(1).setName("Jankovic").build();
		Pharmacy ph2 = Pharmacy.newBuilder().setId(2).setName("Zegin").build();
		Drug d1 = Drug.newBuilder().setId(1).setName("Brufen").setQuantity(3).setPharmacy(ph1).build(); 
		Drug d2 = Drug.newBuilder().setId(2).setName("Aspirin").setQuantity(1).setPharmacy(ph2).build();
		Drug d3 = Drug.newBuilder().setId(2).setName("Andol").setQuantity(7).setPharmacy(ph2).build();
		Drug d4 = Drug.newBuilder().setId(2).setName("Paracetamol").setQuantity(10).setPharmacy(ph1).build();
		Drug d5 = Drug.newBuilder().setId(2).setName("Bromazepam").setQuantity(22).setPharmacy(ph1).build();
		
		drugs.add(d1);
		drugs.add(d2);
		drugs.add(d3);
		drugs.add(d4);
		drugs.add(d5);
		 
		for(Drug d : drugs) {
			if(d.getName().toLowerCase().contains(request.getName().toLowerCase())) {
				ret.add(d);
			}
		}
		/* ret.add(d1); 
		 ret.add(d2);*/
		 

		FindDrugResponse response = FindDrugResponse.newBuilder().addAllDrugs(ret).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
