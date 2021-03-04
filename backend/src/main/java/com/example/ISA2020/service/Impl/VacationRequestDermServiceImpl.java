package com.example.ISA2020.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.VacationRequestDermDTO;
import com.example.ISA2020.dto.VacationRequestPharmDTO;
import com.example.ISA2020.entity.VacationRequestDerm;
import com.example.ISA2020.entity.VacationRequestPharm;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.enumeration.VacationRequestStatus;
import com.example.ISA2020.repository.VacationRequestDermRepository;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.VacationRequestDermService;

@Service
public class VacationRequestDermServiceImpl implements VacationRequestDermService{
	
	@Autowired
	private VacationRequestDermRepository vacationDermRepo;
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	

	@Override
	public VacationRequestDerm findById(Long id) {
		// TODO Auto-generated method stub
		return vacationDermRepo.findOneById(id);
	}

	@Override
	public List<VacationRequestDerm> getAllVacationRequestDerms() {
		// TODO Auto-generated method stub
		return vacationDermRepo.findAll();
	}

	@Override
	public boolean isDermatologistOnVacation(Dermatologist dermatologist, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		
		List<VacationRequestDerm> VRDerms = vacationDermRepo.findByDermatologistIdAndStatusNot(dermatologist.getId(), VacationRequestStatus.REJECTED);
		if(!VRDerms.isEmpty()) {
			for(VacationRequestDerm request : VRDerms) {
				if(!request.getInterval().isAvailable(startDateTime,endDateTime)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<VacationRequestDermDTO> getAllRequestsForPharmacy(Long pharmId) {
		
		return convertToDTO(vacationDermRepo.findByPharmacyId(pharmId));
	}
	
	private List<VacationRequestDermDTO> convertToDTO(List<VacationRequestDerm> requests){
		List<VacationRequestDermDTO> returnList = new ArrayList<VacationRequestDermDTO>();
		for(VacationRequestDerm request: requests) {
			returnList.add(new VacationRequestDermDTO(request));
		}
		return returnList;
	}

	@Override
	public VacationRequestDermDTO approveRequestInPharmacy(Long pharmId, Long id) {

		VacationRequestDerm request = vacationDermRepo.findOneById(id);
		
		request.setStatus(VacationRequestStatus.APPROVED);
		
		sendEmailApprove(request.getDermatologist().getUsername(), request);
		
		return new VacationRequestDermDTO(vacationDermRepo.save(request));
		
	}
	
	@Override
	public VacationRequestDermDTO denyRequestInPharmacy(Long pharmId, Long id, String reason) {
		
		VacationRequestDerm request = vacationDermRepo.findOneById(id);
		
		request.setStatus(VacationRequestStatus.REJECTED);
		
		sendEmailDeny(request.getDermatologist().getUsername(), request, reason);
		
		return new VacationRequestDermDTO(vacationDermRepo.save(request));
	}

	@Async
	private void sendEmailApprove(String email, VacationRequestDerm request) {
			
			String subject = "Odgovor na zahtev za godišnji odmor";
			
			StringBuilder sb = new StringBuilder();
	        sb.append("Postovani/a " + request.getDermatologist().getFirstName() + " " + request.getDermatologist().getLastName() + "\n");
	        sb.append("Prihvaćen je Vaš zahtev za godišnjim odmorom u periodu od " + request.getInterval().getStartDateTime() + "do " + request.getInterval().getEndDateTime()  + "\n");
	        sb.append("Vaša Admin služba");
	        sb.append(System.lineSeparator());
	        String text = sb.toString();
			
	        System.out.println(text);
	        
			emailNotificationService.sendEmail(email, subject, text);
	}
	
	@Async
	private void sendEmailDeny(String email, VacationRequestDerm request, String reason) {
			
			String subject = "Odgovor na zahtev za godišnji odmor";
			
			StringBuilder sb = new StringBuilder();
	        sb.append("Postovani/a " + request.getDermatologist().getFirstName() + " " + request.getDermatologist().getLastName() + "\n");
	        sb.append("Odbijen je Vaš zahtev za godišnjim odmorom u periodu od " + request.getInterval().getStartDateTime() + "do " + request.getInterval().getEndDateTime()  + "\n");
	        sb.append("Razlog: "+ reason + "\n");
	        sb.append("Vaša Admin služba");
	        sb.append(System.lineSeparator());
	        String text = sb.toString();
			
	        System.out.println(text);
	        
			emailNotificationService.sendEmail(email, subject, text);
	}


}
