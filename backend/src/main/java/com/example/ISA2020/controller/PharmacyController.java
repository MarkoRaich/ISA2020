package com.example.ISA2020.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA2020.dto.ConsultationPriceAddressDTO;
import com.example.ISA2020.dto.DrugPricePharmacyNameAddressRatingDTO;
import com.example.ISA2020.dto.EditPharmacyDTO;
import com.example.ISA2020.dto.ExaminationPriceDTO;
import com.example.ISA2020.dto.ExaminationPriceDermatologistDTO;
import com.example.ISA2020.dto.PharmacistSimpleDTO;
import com.example.ISA2020.dto.PharmacyDTO;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.service.ConsultationPriceService;
import com.example.ISA2020.service.ExaminationPriceService;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.service.PharmacyService;

@RestController
@RequestMapping(value = "/api/pharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyController {
	
	@Autowired 
	private PharmacyService pharmacyService;
	
	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	
	@Autowired
	private ExaminationPriceService examinationPriceService;
	
	@Autowired
	private ConsultationPriceService consultationPriceService;
	
	
	@PostMapping(value = "/create")
    public ResponseEntity<Pharmacy> create(@RequestBody PharmacyDTO pharmacyDTO) {
        try {
            Pharmacy newPharmacy = pharmacyService.createPharmacy(pharmacyDTO);
            if (newPharmacy == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newPharmacy, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping(value="/getAll")
	public ResponseEntity<List<Pharmacy>> getAllPharmacies() {
		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
	
		return new ResponseEntity<>(pharmacies, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pharmacy> getOneById(@PathVariable Long id){
		Pharmacy pharmacy = pharmacyService.findById(id);
		if(pharmacy == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(pharmacy, HttpStatus.OK);
	}
	
	//------------------------------------------------------------------------
	
	@GetMapping(value = "/pharmacy-of-admin")
    //@PreAuthorize("hasAnyRole('PHARMACY_ADMIN')
	public ResponseEntity<PharmacyDTO> getPharmacyOfPharmacyAdmin() {
		
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
		
		if(pharmacyAdmin == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(new PharmacyDTO(pharmacyAdmin.getPharmacy()), HttpStatus.OK);
		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<EditPharmacyDTO> edit(@Valid @RequestBody EditPharmacyDTO pharmacyDTO) {
		
		PharmacyAdmin pharmacyAdmin = pharmacyAdminService.getLoginAdmin();
        if (pharmacyAdmin == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        EditPharmacyDTO changedPharmacy = pharmacyService.edit(pharmacyDTO, pharmacyAdmin.getPharmacy().getId());
        if (changedPharmacy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(changedPharmacy, HttpStatus.ACCEPTED);
    }
	
	//3.13
	@GetMapping("/getAllExaminationPricesSortedByPriceForPharmacy")
	public ResponseEntity<List<ExaminationPriceDermatologistDTO>> getAllExaminationsByPrice(@RequestParam("pharmacyId") Long id) {
		List<ExaminationPriceDermatologistDTO> dtos = examinationPriceService.getAllExaminationPricesSortedByPriceForPharmacy(id);
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDermatologistDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllExaminationPricesSortedByDermatologistRatingForPharmacy")
	public ResponseEntity<List<ExaminationPriceDTO>> getAllExaminationsByDermatologistRating(@RequestParam("pharmacyId") Long id) {
		List<ExaminationPriceDTO> dtos = examinationPriceService.getAllExaminationPricesSortedByDermatologistRatingForPharmacy(id);
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ExaminationPriceDTO>>(dtos, HttpStatus.OK);
	}
	
	
	//3.16 
	@GetMapping("/getAllConsultationPricesSortedByPriceForPharmacy")
	public ResponseEntity<List<ConsultationPriceAddressDTO>> getAllConsultationsByPrice(@RequestParam("pharmacyId") Long id) {
		List<ConsultationPriceAddressDTO> dtos = consultationPriceService.getAllConsultationPricesSortedByPriceForPharmacy(id);
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceAddressDTO>>(dtos, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllConsultationPricesSortedByRatingForPharmacy")
	public ResponseEntity<List<ConsultationPriceAddressDTO>> getAllConsultationsByRating(@RequestParam("pharmacyId") Long id) {
		List<ConsultationPriceAddressDTO> dtos = consultationPriceService.getAllConsultationPricesSortedByRatingForPharmacy(id);
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ConsultationPriceAddressDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getAllPharmacistsSortedByRatingForPharmacy")
	public ResponseEntity<List<PharmacistSimpleDTO>> getAllPharmacistsByRating(@RequestParam("pharmacyId") Long id) {
		List<PharmacistSimpleDTO> dtos = consultationPriceService.getAllPharmacistsSortedByRatingForPharmacy(id);
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PharmacistSimpleDTO>>(dtos, HttpStatus.OK);
	}
	
	
	//3.9 ------------------------------------------------------------------ omoguci pristup svim korisnicima
	@GetMapping("/noAuth/getAllPharmaciesSortedByAddress")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesSortedByAddress() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyAddress();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/noAuth/getAllPharmaciesSortedByName")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesSortedByName() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyName();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/noAuth/getAllPharmaciesSortedByRating")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesSortedByRating() {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByPharmacyRating();
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	
	//3.31 unosi se addressa ----------------------------------------------------------- omoguci pristup svim korisnicima
	@GetMapping("/noAuth/getAllPharmaciesSortedByAddressForAddress")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesSortedByAddressForAddress(@RequestParam("pharmacyAddress") String address) {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByAddressForAddress(address);
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
	
	//unosi se ime
	@GetMapping("/noAuth/getAllPharmaciesSortedByNameForName")
	public ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>> getAllPharmaciesSortedByNameForName(@RequestParam("pharmacyName") String name) {
		List<DrugPricePharmacyNameAddressRatingDTO> dtos = pharmacyService.getAllPharmaciesSortedByNameForName(name);
		if(dtos == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DrugPricePharmacyNameAddressRatingDTO>>(dtos, HttpStatus.OK);
	}
}
