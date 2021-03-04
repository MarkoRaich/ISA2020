package com.example.ISA2020.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.AvailableExaminationDTO;
import com.example.ISA2020.dto.ConsultationPriceDTO;
import com.example.ISA2020.dto.ExaminationDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.dto.ExaminationTypeDTO;
import com.example.ISA2020.entity.ConsultationPrice;
import com.example.ISA2020.entity.DateTimeInterval;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.ExaminationType;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.enumeration.ConsultationStatus;
import com.example.ISA2020.enumeration.ExaminationStatus;
import com.example.ISA2020.enumeration.ReservationStatus;
import com.example.ISA2020.repository.ExaminationRepository;
import com.example.ISA2020.service.DermatologistService;
import com.example.ISA2020.service.ExaminationService;
import com.example.ISA2020.service.ExaminationTypeService;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	
	@Autowired
	private ExaminationRepository examinationRepository;
	
	@Autowired
	private ExaminationTypeService examinationTypeService;
	
	@Autowired
	private DermatologistService dermatologistService;
	
	@Override
	public Examination findById(Long id) {
		return examinationRepository.findOneById(id);
	}

	@Override
	public List<Examination> getAllExaminations() {
		return examinationRepository.findAll();
	}

	@Override
	public List<Examination> getDermatologistUpcomingExaminations(Long dermId) {
		
		 return examinationRepository.findByDermatologistIdAndStatusNotAndIntervalEndDateTimeAfter(dermId, ExaminationStatus.CANCELED, LocalDateTime.now());
	}

	@Override
	public List<AvailableExaminationDTO> getAvailableExaminationsForPharmacy(Long pharmId) {
		
		List<AvailableExaminationDTO> examsDTO = new ArrayList<>();
		
		List<Examination> exams = examinationRepository.findByPharmacyIdAndStatus(pharmId, ExaminationStatus.AVAILABLE );
		
		for(Examination exam : exams) {
			examsDTO.add(new AvailableExaminationDTO(exam.getId(),
													 exam.getDermatologist(),
													 exam.getInterval(),
													 new ExaminationTypeDTO(exam.getExamType()) ));
		}
		
		return examsDTO;
		
	}

	@Override
	public ExaminationDTO createAvailableExamination(@Valid AvailableExaminationDTO availableExaminationDTO, PharmacyAdmin pharmacyAdmin) {
		
			LocalDate localDate = getDate(availableExaminationDTO.getStartDateTime());
	        LocalDateTime startDateTime = getLocalDateTime(localDate, availableExaminationDTO.getStartDateTime());
	        LocalDateTime endDateTime = getLocalDateTime(localDate, availableExaminationDTO.getEndDateTime());
		
	        if (startDateTime.isBefore(LocalDateTime.now()) || startDateTime.isAfter(endDateTime)) {
	            return null;
	        }
	        
	        ExaminationType examinationType = examinationTypeService.findById(availableExaminationDTO.getExaminationTypeDTO().getId());
	        Dermatologist dermatologist = dermatologistService.getDermatologist(availableExaminationDTO.getDermatologistDTO().getId());
	        
	        DateTimeInterval dateTimeInterval = new DateTimeInterval(startDateTime, endDateTime);
	        
	        Examination examination = new Examination(ExaminationStatus.AVAILABLE, dateTimeInterval, dermatologist, examinationType, pharmacyAdmin.getPharmacy() );
	        
		
	        return new ExaminationDTO(examinationRepository.save(examination));
	}
	
	 private LocalDateTime getLocalDateTime(LocalDate date, String time) throws DateTimeParseException {
	        LocalTime localTime = LocalTime.parse(time.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
	        return LocalDateTime.of(date, localTime);
	    }

	    private LocalDate getDate(String date) throws DateTimeParseException {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        return LocalDate.parse(date.substring(0, 10), formatter);
	    }

		@Override
		public List<Examination> getTodaysExaminationsForDermatologist(Long dermId, LocalDateTime startDateTime) {
			
			LocalDate date = getDate(startDateTime.toString());
			
	        LocalDateTime greater = LocalDateTime.of(date, LocalTime.of(0, 0));
	        LocalDateTime less = LocalDateTime.of(date, LocalTime.of(23, 59, 59));

	        return examinationRepository.findByDermatologistIdAndStatusNotAndIntervalStartDateTimeGreaterThanEqualAndIntervalStartDateTimeLessThan(dermId, ExaminationStatus.CANCELED, greater, less);
			
		}

		@Override
		public List<Examination> getFinishedExaminationsForPharmacy(Long pharmId) {
			
			return this.examinationRepository.findByPharmacyIdAndStatus(pharmId, ExaminationStatus.DONE);
		}

		@Override
		public List<Examination> getPharmacyExaminations(Long pharmId, LocalDateTime startDate, LocalDateTime endDate) {

			return this.examinationRepository.findByPharmacyIdAndStatusAndIntervalStartDateTimeGreaterThanEqualAndIntervalEndDateTimeLessThanOrderByIntervalStartDateTime(pharmId, ExaminationStatus.DONE, startDate, endDate );

		}

		@Override
		public List<ExaminationPriceDTO> getAllBookedExaminationsForPatient(Patient patient) {
			
			List<Examination> examinations = examinationRepository.findByPatientIdAndStatus(patient.getId(), ExaminationStatus.BOOKED);
			
			List<ExaminationPriceDTO> returnList = new ArrayList<>();
			for(Examination examination: examinations) {
				returnList.add(new ExaminationPriceDTO(examination));
			}
			
			return returnList;
		}

		@Override
		public List<ExaminationPriceDermatologistDTO> getAllAvailableExaminations() {
			
			List<Examination> examinations = examinationRepository.findByStatus(ExaminationStatus.AVAILABLE);
			
			List<ExaminationPriceDermatologistDTO> returnList = new ArrayList<>();
			for(Examination examination: examinations) {
				returnList.add(new ExaminationPriceDermatologistDTO(examination));
			}
			
			return returnList;
		}

		@Override
		public List<ExaminationPriceDTO> getAllDoneExaminationsForPatient(Patient patient) {
			List<Examination> examinations = examinationRepository.findByPatientIdAndStatus(patient.getId(), ExaminationStatus.DONE);
			
			List<ExaminationPriceDTO> returnList = new ArrayList<>();
			for(Examination examination: examinations) {
				returnList.add(new ExaminationPriceDTO(examination));
			}
			
			return returnList;
		}
	
	/*
	public List<ExaminationDTO> getAllExaminationSortedByDate() {
		List<Examination> examinations = examinationRepository.findByOrderByDateTimeInterval();
		
		List<ExaminationDTO> dtos = new ArrayList<>();
		
		for(Examination e : examinations) {
			
		}
	} */
	

}
