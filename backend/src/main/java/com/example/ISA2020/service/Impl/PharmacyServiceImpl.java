package com.example.ISA2020.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.EditPharmacyDTO;
import com.example.ISA2020.dto.IncomeListDTO;
import com.example.ISA2020.dto.PharmacistDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.repository.PharmacyRepository;
import com.example.ISA2020.service.ConsultationPriceService;
import com.example.ISA2020.service.ConsultationService;
import com.example.ISA2020.service.ExaminationService;
import com.example.ISA2020.service.PharmacyService;
import com.example.ISA2020.service.ReservationService;


@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
    private PharmacyRepository pharmacyRepository;
	
	@Autowired
	private ExaminationService examinationService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ConsultationService consultationService;
	
	@Autowired
	private ConsultationPriceService consultationPriceService;
	
	
	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findOneById(id);
	}

	@Override
	public Pharmacy findByName(String name) {
		return pharmacyRepository.findOneByName(name);
	}
	
	public Pharmacy findByAddress(String address) {
		return pharmacyRepository.findByAddressIgnoringCase(address);
	}

	@Override
	public Pharmacy createPharmacy(PharmacyDTO pharmacyDTO) {
		if (pharmacyRepository.findOneByName(pharmacyDTO.getName()) != null) {
            return null;
        } //findByName vraca null ako ga nadje
		
		Pharmacy newPharmacy = new Pharmacy(pharmacyDTO.getName(), pharmacyDTO.getAddress());
		
		return pharmacyRepository.save(newPharmacy);
	}

	@Override
	public List<PharmacyDTO> getAllPharmacies() {
		List<Pharmacy> pharmacies =  pharmacyRepository.findAll();
		
		List<PharmacyDTO> dtos = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			PharmacyDTO dto = new PharmacyDTO();
			dto.setId(p.getId());
			dto.setAddress(p.getAddress());
			dto.setName(p.getName());
			dto.setPharmacyRating(p.getRating());
			dto.setDescription(p.getDescription());
			dto.setPrice(0);
			
			dtos.add(dto);
		}
		
		return dtos;
		
	}

	@Override
	public EditPharmacyDTO edit(@Valid EditPharmacyDTO pharmacyDTO, Long pharmId) {
		
		Pharmacy existingPharmacy = pharmacyRepository.findOneById(pharmacyDTO.getId());

        if (existingPharmacy == null || existingPharmacy.getId() != pharmId) {
            return null;
        }

        Pharmacy pharmacyWithSameName = findByName(pharmacyDTO.getName());
        Pharmacy phamracyWithSameAddress = findByAddress(pharmacyDTO.getAddress());
        if ((pharmacyWithSameName != null && pharmacyWithSameName.getId() != existingPharmacy.getId())
                || (phamracyWithSameAddress != null && phamracyWithSameAddress.getId() != existingPharmacy.getId())) {

            return null;
        }
        existingPharmacy.setName(pharmacyDTO.getName());
        existingPharmacy.setAddress(pharmacyDTO.getAddress());
        existingPharmacy.setDescription(pharmacyDTO.getDescription());
        return new EditPharmacyDTO(pharmacyRepository.save(existingPharmacy));
        
	}
	
	
	
	// 3.9 -----------------------------------------------------------------------------------------
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyAddress(){
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByAddressAsc();
		
		List<DrugPricePharmacyNameAddressRatingDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO();
			dto.setPharmacyName(p.getName());
			dto.setPharmacyAddress(p.getAddress());
			dto.setPharmacyRating(p.getRating());
			
			pharmaciesDTO.add(dto);
		}
		
		return pharmaciesDTO;
	}
	
	
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyName(){
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByNameAsc();
		
		List<DrugPricePharmacyNameAddressRatingDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO();
			dto.setPharmacyName(p.getName());
			dto.setPharmacyAddress(p.getAddress());
			dto.setPharmacyRating(p.getRating());
			
			pharmaciesDTO.add(dto);
		}
		
		return pharmaciesDTO;
	}
	
	
	@Override
	public List<DrugPricePharmacyNameAddressRatingDTO> getAllPharmaciesSortedByPharmacyRating() {
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByRatingAsc();
		
		List<DrugPricePharmacyNameAddressRatingDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			DrugPricePharmacyNameAddressRatingDTO dto = new DrugPricePharmacyNameAddressRatingDTO();
			dto.setPharmacyName(p.getName());
			dto.setPharmacyAddress(p.getAddress());
			dto.setPharmacyRating(p.getRating());
			
			pharmaciesDTO.add(dto);
		}
		
		return pharmaciesDTO;
	}
	//---------------------------------------------------------------------------------------
	
	
	//3.31 pretraga i sortiranje apoteka
	@Override
	public List<PharmacyDTO> getAllPharmaciesSortedByAddressForAddress(String pharmacyAddress){
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByAddressAsc();
		
		List<PharmacyDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			if(p.getAddress().toLowerCase().contains(pharmacyAddress.toLowerCase())) {
				PharmacyDTO dto = new PharmacyDTO();
				dto.setName(p.getName());
				dto.setAddress(p.getAddress());
				dto.setPharmacyRating(p.getRating());
				dto.setDescription(p.getDescription());
				dto.setId(p.getId());
				dto.setPrice(0);
				
				
				pharmaciesDTO.add(dto);
			}
		}
		
		return pharmaciesDTO;
	}
	
	@Override
	public List<PharmacyDTO> getAllPharmaciesSortedByNameForName(String pharmacyName) {
		
		List<Pharmacy> pharmacies = pharmacyRepository.findByOrderByNameAsc();
		
		List<PharmacyDTO> pharmaciesDTO = new ArrayList<>();
		
		for(Pharmacy p : pharmacies) {
			if(p.getName().toLowerCase().contains(pharmacyName.toLowerCase())) {
				PharmacyDTO dto = new PharmacyDTO();
				dto.setName(p.getName());
				dto.setAddress(p.getAddress());
				dto.setPharmacyRating(p.getRating());
				dto.setDescription(p.getDescription());
				dto.setId(p.getId());
				dto.setPrice(0);
				
				pharmaciesDTO.add(dto);
			}
		}
		
		return pharmaciesDTO;
	}
	
	@Override
	public List<PharmacyDTO> searchPharmaciesByNameAndAddress(Long id, String name, String address) {
		return convertToDTO(pharmacyRepository.findByIdAndNameContainsIgnoringCaseAndAddressContainsIgnoringCase(id, name, address));
										   
	}
	
	private List<PharmacyDTO> convertToDTO(List<Pharmacy> pharmacies) {
        List<PharmacyDTO> pharmaciesDTOs = new ArrayList<>();
        for (Pharmacy p : pharmacies) {
        	pharmaciesDTOs.add(new PharmacyDTO(p));
        }
        return pharmaciesDTOs;
    }

	@Override
	public int[] getMonthlyStatistic(Long pharmId) {
		
		List<Examination> examinations = examinationService.getFinishedExaminationsForPharmacy(pharmId);
		
		int[] returnValue = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		Date currentDate = new Date();
		int currentYear = currentDate.getYear() + 1900;	//ovako se cuva godina
		
		for(Examination examination: examinations) {
			if(examination.getInterval().getEndDateTime().getYear() == currentYear) {
				returnValue[examination.getInterval().getStartDateTime().getMonthValue() - 1]++;
			}
		}
		
		return returnValue;
	}

	@Override
	public int[] getQuartalStatistic(Long pharmId) {
		
		List<Examination> examinations = examinationService.getFinishedExaminationsForPharmacy(pharmId);
		
		int[] returnValue = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		Date currentDate = new Date();
		int currentYear = currentDate.getYear() + 1900;	//ovako se cuva godina
		
		for(Examination examination: examinations) {
			if(examination.getInterval().getEndDateTime().getYear() == currentYear) {
			
				returnValue[examination.getInterval().getStartDateTime().getMonthValue() - 1]++;
			}
		}
	
		int[] returnQuartal ={returnValue[0] + returnValue[1] + returnValue[2],
							  returnValue[3] + returnValue[4] + returnValue[5],
							  returnValue[6] + returnValue[7] + returnValue[8],
							  returnValue[9] + returnValue[10] + returnValue[11]
							};
		
		return returnQuartal;
	}
	
	@Override
	public int[] getYearStatistic(Long pharmId) {
		
		List<Examination> examinations = examinationService.getFinishedExaminationsForPharmacy(pharmId);
		
		int[] returnValue = {0, 0, 0};
		
		
		
		for(Examination examination: examinations) {
			if(examination.getInterval().getEndDateTime().getYear() == 2019) {
				returnValue[0]++;
			} else if(examination.getInterval().getEndDateTime().getYear() == 2020) {
				returnValue[1]++;
			} else if(examination.getInterval().getEndDateTime().getYear() == 2021) {
				returnValue[2]++;
			}
				
		}
		return returnValue;
	}

	
	@Override
	public int[] getMonthlyStatisticDrugs(Long pharmId) {
		
		List<Reservation> reservations = reservationService.getReservationsCompletedForPharmacy(pharmId);
		
		int[] returnValue = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		Date currentDate = new Date();
		int currentYear = currentDate.getYear() + 1900;	//ovako se cuva godina
		
		for(Reservation reservation: reservations) {
			if(reservation.getInterval().getEndDateTime().getYear() == currentYear) {
				returnValue[reservation.getInterval().getStartDateTime().getMonthValue() - 1] = returnValue[reservation.getInterval().getStartDateTime().getMonthValue() - 1] + reservation.getQuantity();
			}
		}
		
		return returnValue;
	}

	@Override
	public int[] getQuartalStatisticDrugs(Long pharmId) {
		
		List<Reservation> reservations = reservationService.getReservationsCompletedForPharmacy(pharmId);
		
		int[] returnValue = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		Date currentDate = new Date();
		int currentYear = currentDate.getYear() + 1900;	//ovako se cuva godina
		
		for(Reservation reservation: reservations) {
			if(reservation.getInterval().getEndDateTime().getYear() == currentYear) {
				returnValue[reservation.getInterval().getStartDateTime().getMonthValue() - 1] = returnValue[reservation.getInterval().getStartDateTime().getMonthValue() - 1] + reservation.getQuantity();
			}
		}
	
		int[] returnQuartal = {   returnValue[0] + returnValue[1] + returnValue[2],
								  returnValue[3] + returnValue[4] + returnValue[5],
								  returnValue[6] + returnValue[7] + returnValue[8],
								  returnValue[9] + returnValue[10] + returnValue[11]
								};
		
		return returnQuartal;
	}

	@Override
	public int[] getYearStatisticDrugs(Long pharmId) {

		List<Reservation> reservations = reservationService.getReservationsCompletedForPharmacy(pharmId);
		
		int[] returnValue = {0, 0, 0};
		
		
		
		for(Reservation reservation: reservations) {
			if(reservation.getInterval().getEndDateTime().getYear() == 2019) {
				returnValue[0] = returnValue[0] + reservation.getQuantity();
			} else if(reservation.getInterval().getEndDateTime().getYear() == 2020) {
				returnValue[1] = returnValue[1] + reservation.getQuantity();
			} else if(reservation.getInterval().getEndDateTime().getYear() == 2021) {
				returnValue[2] = returnValue[2] + reservation.getQuantity();
			}
				
		}
		return returnValue;
		
	}

	@Override
	public IncomeListDTO getPharmacyIncome(Long id, String startDateTime, String endDateTime) {		//prihodi su od prodaje lekova, savetovanja i pregleda
		
		IncomeListDTO returnValue = new IncomeListDTO();
		
		LocalDateTime startDate = LocalDateTime.of(getDate(startDateTime.toString()), LocalTime.of(0, 0));
	    LocalDateTime endDate = LocalDateTime.of(getDate(endDateTime.toString()), LocalTime.of(0, 0, 0));
	        
	    if (startDate.isAfter(endDate) || startDate.isAfter(LocalDateTime.now())) {
	    	return null;
	    }

	    List<Examination> examinations = examinationService.getPharmacyExaminations(id, startDate, endDate);
	    //System.out.println("Broj pregleda: " + examinations.size());
	    
	    List<Consultation> consultations = consultationService.getPharmacyConsultation(id, startDate, endDate);
	    //System.out.println("Broj savetovanja: " + consultations.size());
	    
        List<Reservation> reservations = reservationService.getPharmacyReservations(id, startDate, endDate);
        //System.out.println("Broj rezervacija: " + reservations.size());
        
        
        Reservation firstReservation = reservations.get(0);
        //System.out.println("Prva rezervacija: " + firstReservation.getInterval().getStartDateTime());
        
        Reservation lastReservation =  reservations.get(reservations.size()-1);
        //System.out.println("Poslednja rezervacija: " + lastReservation.getInterval().getStartDateTime());
    
        
        Examination firstExamination = examinations.get(0);
        //System.out.println("Prvi pregled: " + firstExamination.getInterval().getStartDateTime());
        
        Examination lastExamination =  examinations.get(examinations.size()-1);
        //System.out.println("Poslednji pregled: " + lastExamination.getInterval().getStartDateTime());
     
        
        Consultation firstConsultation = consultations.get(0);
        //System.out.println("Prvo savetovanje: " + firstConsultation.getInterval().getStartDateTime());
        
        Consultation lastConsultation =  consultations.get(consultations.size()-1);
        //System.out.println("Poslednje savetovanje: " + lastConsultation.getInterval().getStartDateTime());
        
        LocalDateTime firstDate = firstDateFinder(firstReservation, firstExamination, firstConsultation);
        LocalDateTime lastDate = lastDateFinder(lastReservation, lastExamination, lastConsultation);
        
       

		int numberOfLabels = 12*( lastDate.getYear() -  firstDate.getYear() )
        					+ lastDate.getMonthValue() - firstDate.getMonthValue() +1;
        //System.out.println("Broj meseci: " + numberOfLabels);
        
        int month = firstDate.getMonthValue();
        int year =  firstDate.getYear();
        
        for(int i=0; i<numberOfLabels; i++) {
      
        	if(month <= 12) {
        		returnValue.getLabels().add(month + "." + year);
        		returnValue.getValues().add(0.00);
        		//System.out.println(month + "." + year);
        		++month;
        	} else {
        		++year;
        		month = month -12;
        		returnValue.getLabels().add(month + "." + year);
        		returnValue.getValues().add(0.00);
        		//System.out.println(month + "." + year);
        		++month;
        	}      	
        }
		
        for(Reservation reservation: reservations) {
        	int index = returnValue.getLabels().indexOf( reservation.getInterval().getStartDateTime().getMonthValue()+"."+reservation.getInterval().getStartDateTime().getYear() );
        	
        	returnValue.getValues().set(index, returnValue.getValues().get(index) + reservation.getQuantity()* reservation.getSellingPrice());
        }
        
        for(Examination examination: examinations) {
        	int index = returnValue.getLabels().indexOf( examination.getInterval().getStartDateTime().getMonthValue()+"."+examination.getInterval().getStartDateTime().getYear() );
        	
        	returnValue.getValues().set(index, returnValue.getValues().get(index) + examination.getExamType().getPrice());
        }
        
        for(Consultation consultation: consultations) {
        	int index = returnValue.getLabels().indexOf( consultation.getInterval().getStartDateTime().getMonthValue()+"."+consultation.getInterval().getStartDateTime().getYear() );
        	
        	returnValue.getValues().set(index, returnValue.getValues().get(index) + consultationPriceService.getPriceForPharmacyAndConsultation(id, consultation.getId()));
        	
        }
        
        
        
		return returnValue;
		
		
	}
	
	
	private LocalDate getDate(String date) throws DateTimeParseException {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        return LocalDate.parse(date.substring(0, 10), formatter);
	}
	
	
	private LocalDateTime firstDateFinder(Reservation firstReservation, Examination firstExamination, Consultation firstConsultation) {
		
		if( firstReservation.getInterval().getStartDateTime().isBefore(firstExamination.getInterval().getStartDateTime()) ) { 
		   if(firstReservation.getInterval().getStartDateTime().isBefore(firstConsultation.getInterval().getStartDateTime()) ){
		        	return firstReservation.getInterval().getStartDateTime();
		        } else {
		        	return firstConsultation.getInterval().getStartDateTime();
		        }
		   
		} else {
			if(firstExamination.getInterval().getStartDateTime().isBefore(firstConsultation.getInterval().getStartDateTime()) ){
				return firstExamination.getInterval().getStartDateTime();
			} else {
				return firstConsultation.getInterval().getStartDateTime();
			}
		}
		
	}
	
	
	private LocalDateTime lastDateFinder(Reservation lastReservation, Examination lastExamination, Consultation lastConsultation) {
		  
		if( lastReservation.getInterval().getStartDateTime().isBefore(lastExamination.getInterval().getStartDateTime()) ) { 
			   if(lastExamination.getInterval().getStartDateTime().isBefore(lastConsultation.getInterval().getStartDateTime()) ){
			        	return lastConsultation.getInterval().getStartDateTime();
			        } else {
			        	return lastExamination.getInterval().getStartDateTime();
			        }
			   
			} else {
				if(lastConsultation.getInterval().getStartDateTime().isBefore(lastReservation.getInterval().getStartDateTime()) ){
					return lastReservation.getInterval().getStartDateTime();
				} else {
					return lastConsultation.getInterval().getStartDateTime();
				}
			}
	}

	  
	

}
