package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.VacationRequestPharmDTO;
import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.entity.VacationRequestPharm;
import com.example.ISA2020.enumeration.VacationRequestStatus;
import com.example.ISA2020.repository.VacationRequestPharmRepository;
import com.example.ISA2020.service.EmailNotificationService;
import com.example.ISA2020.service.VacationRequestPharmService;

@Service
public class VacationRequestPharmServiceImpl implements VacationRequestPharmService{

	@Autowired
	private VacationRequestPharmRepository vacationPharmRepo;
	
	@Autowired
	private EmailNotificationService emailNotificationService;

	@Override
	public VacationRequestPharm findById(Long id) {
		
		return vacationPharmRepo.findOneById(id);
	}

	@Override
	public List<VacationRequestPharm> getAllVacationRequestPharms() {
		
		return vacationPharmRepo.findAll();
	}

	@Override
	public List<VacationRequestPharmDTO> getAllRequestsForPharmacy(Long id) {
		
		return convertToDTO(vacationPharmRepo.findByPharmacistPharmacyId(id));
		
	}


	private List<VacationRequestPharmDTO> convertToDTO(List<VacationRequestPharm> requests){
		List<VacationRequestPharmDTO> returnList = new ArrayList<VacationRequestPharmDTO>();
		for(VacationRequestPharm request: requests) {
			returnList.add(new VacationRequestPharmDTO(request));
		}
		return returnList;
	}

	@Override
	public VacationRequestPharmDTO approveRequestInPharmacy(Long pharmId, Long id) {
		
		VacationRequestPharm request = vacationPharmRepo.findOneById(id);
		
		request.setStatus(VacationRequestStatus.APPROVED);
		
		sendEmailApprove(request.getPharmacist().getUsername(), request);
		
		return new VacationRequestPharmDTO(vacationPharmRepo.save(request));
		
	}

	@Override
	public VacationRequestPharmDTO denyRequestInPharmacy(Long pharmId, Long id, String reason) {
		
		VacationRequestPharm request = vacationPharmRepo.findOneById(id);
		
		request.setStatus(VacationRequestStatus.REJECTED);
		
		sendEmailDeny(request.getPharmacist().getUsername(), request, reason);
		
		return new VacationRequestPharmDTO(vacationPharmRepo.save(request));
	}

	@Async
	private void sendEmailApprove(String email, VacationRequestPharm request) {
			
			String subject = "Odgovor na zahtev za godišnji odmor";
			
			StringBuilder sb = new StringBuilder();
	        sb.append("Postovani/a " + request.getPharmacist().getFirstName() + " " + request.getPharmacist().getLastName() + "\n");
	        sb.append("Prihvaćen je Vaš zahtev za godišnjim odmorom u periodu od " + request.getInterval().getStartDateTime() + "do " + request.getInterval().getEndDateTime()  + "\n");
	        sb.append("Vaša Admin služba");
	        sb.append(System.lineSeparator());
	        String text = sb.toString();
			
	        System.out.println(text);
	        
			emailNotificationService.sendEmail(email, subject, text);
	}
	
	@Async
	private void sendEmailDeny(String email, VacationRequestPharm request, String reason) {
			
			String subject = "Odgovor na zahtev za godišnji odmor";
			
			StringBuilder sb = new StringBuilder();
	        sb.append("Postovani/a " + request.getPharmacist().getFirstName() + " " + request.getPharmacist().getLastName() + "\n");
	        sb.append("Odbijen je Vaš zahtev za godišnjim odmorom u periodu od " + request.getInterval().getStartDateTime() + "do " + request.getInterval().getEndDateTime()  + "\n");
	        sb.append("Razlog: "+ reason + "\n");
	        sb.append("Vaša Admin služba");
	        sb.append(System.lineSeparator());
	        String text = sb.toString();
			
	        System.out.println(text);
	        
			emailNotificationService.sendEmail(email, subject, text);
	}


}
