package com.example.ISA2020.service;



import java.util.ArrayList;
import java.util.List;

import com.example.protos.Drug;
import com.example.protos.DrugAvailabilityGrpc.DrugAvailabilityImplBase;
import com.example.protos.FindDrugRequest;
import com.example.protos.FindDrugResponse;
import com.example.protos.Pharmacy;

import io.grpc.stub.StreamObserver;

public class DrugAvailibilityService extends DrugAvailabilityImplBase {
	
	@Override
	public void findDrug(FindDrugRequest request, StreamObserver<FindDrugResponse> responseObserver) {
		ArrayList<Drug> ret = new ArrayList<Drug>();
		Pharmacy ph1 = Pharmacy.newBuilder().setId(1).setName("apoteka-1-1").build();
		Pharmacy ph2 = Pharmacy.newBuilder().setId(2).setName("apoteka-1-2").build();
		Drug d1 = Drug.newBuilder().setId(5).setName("droga").setQuantity(3).setPharmacy(ph1).build();
		Drug d2 = Drug.newBuilder().setId(8).setName("drogacin").setQuantity(1).setPharmacy(ph2).build();
		
		ret.add(d1);
		ret.add(d2);
		
		FindDrugResponse response = FindDrugResponse.newBuilder().addAllDrugs(ret).build();
		
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}
}
