package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugQuantityDTO;
import com.example.ISA2020.dto.ReservationDTO;
import com.example.ISA2020.entity.DrugQuantity;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.enumeration.EntityStatus;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.repository.DrugQuantityRepository;
import com.example.ISA2020.repository.ReservationRepository;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.PatientService;
import com.example.ISA2020.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private DrugQuantityRepository drugQuantityRepo;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	
	

	@Override
	public Reservation findById(Long id) {
		return reservationRepo.findOneById(id);
	}

	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepo.findAll();
	}
	
	
	
	//3.9 za rezervacije
	@Override
	public List<ReservationDTO> getAllReservationsActive() {
		Patient patient = patientService.getLoginPatient();
		
		if(patient == null) {
			return null;
		}
		
		
		List<Reservation> reservations = reservationRepo.findAll();
		List<Reservation> patientReservations = new ArrayList<>();
		List<ReservationDTO> dtos = new ArrayList<>();
		
		//proverava sve preglede i dodaje samo one koji su povezani sa datim pacijentom
		for(Reservation r : reservations) {
			if(r.getPatient().getId() == patient.getId()) {
				System.out.println(r.getStatus());
				if(r.getStatus().equals(ReservationStatus.ACTIVE)) {
					patientReservations.add(r);
				}
			}
		}
		
			//pretvaranje rezultata u dto modele
		for(Reservation e : patientReservations) {
			ReservationDTO dto = new ReservationDTO();
			String idString = e.getId().toString();
			Long id = Long.parseLong(idString);
			dto.setId(id);
			dto.setDrugName(e.getDrug().getName());
			dto.setDrugCode(e.getDrug().getCode());
			dto.setPharmacyName(e.getPharmacy().getName());
			dto.setGeneratedKey(e.getGeneratedKey());
			dto.setQuantity(e.getQuantity());

			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
	
	//3.19 -------------------------------------------------------------------------------------------
	@Override
	public ReservationDTO makeDrugReservation(Long pharmacyId, Long drugId, int quantity) { //treba jos i krajnji datum do preuzimanja da se posalje!!!!!!
		
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		DrugQuantity drugQuantity = drugQuantityRepo.findByPharmacyIdAndDrugId(pharmacyId, drugId); //dodaj potvrdu preko maila
		
		if(drugQuantity == null) {
			return null;
		}
		
		if(drugQuantity.getQuantity() < quantity) {
			System.out.println("nema dovoljno lekova u apoteci");
			return null;
		}
		
		Reservation reservation = new Reservation();
		reservation.setPatient(patient);
		reservation.setPharmacy(drugQuantity.getPharmacy());
		reservation.setDrug(drugQuantity.getDrug());
		reservation.setStatus(ReservationStatus.ACTIVE);
		reservation.setQuantity(quantity);
		reservation.setInterval(null);
		
		
		Integer oldQuantity = drugQuantity.getQuantity();
		Integer newQuantity = oldQuantity - quantity;
		
		drugQuantity.setQuantity(newQuantity);
		if(drugQuantity.getQuantity() == oldQuantity) {
			System.out.println("1");
			return null;
		}
		drugQuantity.setStatus(EntityStatus.ACTIVE);
		
		drugQuantityRepo.save(drugQuantity);
		reservationRepo.save(reservation);
		
		
		//salje se email na mejl pacijenta
		String subject = "Potvrda o rezervaciji leka";
        StringBuilder sb = new StringBuilder();
        sb.append("Postovani, rezervisali ste lek: ");
        sb.append(reservation.getDrug().getName());
        sb.append(" u kolicini: ");
        sb.append(reservation.getQuantity());
        sb.append(", u apoteci: ");
        sb.append(reservation.getPharmacy().getName());
        sb.append(" koja se nalazi na adresi: ");
        sb.append(reservation.getPharmacy().getAddress());
        sb.append(System.lineSeparator());
        
        sb.append("Kod za preuzimanje leka: ");
        sb.append(reservation.getGeneratedKey());
        sb.append(System.lineSeparator());
        
        String text = sb.toString();

        
        emailNotificationService.sendEmail(/*patient.getUsername()*/ "", subject, text);
        
        ReservationDTO dto = new ReservationDTO();
		String idString = reservation.getId().toString();
		Long id = Long.parseLong(idString);
		dto.setId(id);
        dto.setDrugName(reservation.getDrug().getName());
        dto.setDrugCode(reservation.getDrug().getCode());
        dto.setQuantity(reservation.getQuantity());
        dto.setPharmacyName(reservation.getPharmacy().getName());
        dto.setGeneratedKey(reservation.getGeneratedKey());
		
		return dto;
		
	}
	
	@Override
	public List<DrugQuantityDTO> getAllDrugQuantities() {
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		List<DrugQuantity> drugQuantities = drugQuantityRepo.findAll();
		List<DrugQuantityDTO> dtos = new ArrayList<>();
		
		for(DrugQuantity d : drugQuantities) {
			DrugQuantityDTO dto = new DrugQuantityDTO();
			dto.setDrugName(d.getDrug().getName());
			dto.setDrugCode(d.getDrug().getCode());
			dto.setPharmacyName(d.getPharmacy().getName());
			
			dtos.add(dto);
		}
		
		return dtos;
	}
	

	//3.20  Otkazivanje rezervacije leka -------------------------------------------------------------
	@Override
	public ReservationDTO cancelDrugReservation(Long reservationId) {  //treba dodati proveru od 24h i penale
		
		Patient patient = patientService.getLoginPatient();
		if(patient == null) {
			return null;
		}
		
		Set<Reservation> reservations = patient.getReservations();
		
		ReservationDTO dto = new ReservationDTO();
		String address = null;
		
		for(Reservation r : reservations) {
			if(r.getId() == reservationId) {
				Integer reservedQuantity = r.getQuantity();
				r.setStatus(ReservationStatus.CANCELED);
				DrugQuantity drugQuantity = drugQuantityRepo.findByPharmacyIdAndDrugId(r.getPharmacy().getId(), r.getDrug().getId());
				if(drugQuantity == null) {
					System.out.println("nije pronasao drugQuantity");
					return null;
				}
				Integer oldQuantity = drugQuantity.getQuantity();
				Integer newQuantity = reservedQuantity + oldQuantity;
				drugQuantity.setQuantity(newQuantity);
				
				drugQuantityRepo.save(drugQuantity);
				reservationRepo.save(r);
				
				String idString = r.getId().toString();
				Long id = Long.parseLong(idString);
				dto.setId(id);
				
		        dto.setDrugName(r.getDrug().getName());
		        dto.setDrugCode(r.getDrug().getCode());
		        dto.setQuantity(r.getQuantity());
		        dto.setPharmacyName(r.getPharmacy().getName());
		        dto.setGeneratedKey(r.getGeneratedKey());
		        address = r.getPharmacy().getAddress();
			}
		}
		
		
		//salje se email na mejl pacijenta
		String subject = "Potvrda o otkazivanju rezervacije leka";
        StringBuilder sb = new StringBuilder();
        sb.append("Postovani, otkazali ste rezervaciju za lek: ");
        sb.append(dto.getDrugName());
        sb.append(" u kolicini: ");
        sb.append(dto.getQuantity());
        sb.append(", u apoteci: ");
        sb.append(dto.getPharmacyName());
        sb.append(" koja se nalazi na adresi: ");
        sb.append(address);
        sb.append(System.lineSeparator());
        
        sb.append("Kod koji je sluzio za preuzimanje leka: ");
        sb.append(dto.getGeneratedKey());
        sb.append(System.lineSeparator());
        
        String text = sb.toString();

        
        emailNotificationService.sendEmail(/*patient.getUsername()*/ "", subject, text);

		
		return dto;
		
	}
	
}
