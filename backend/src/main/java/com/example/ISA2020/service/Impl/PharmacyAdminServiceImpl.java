package com.example.ISA2020.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.EditPharmAdminDTO;
import com.example.ISA2020.dto.PharmacyAdminDTO;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.repository.PharmacyAdminRepository;
import com.example.ISA2020.service.PharmacyAdminService;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {
	
	@Autowired
	private PharmacyAdminRepository pharmacyAdminRepository;
	
	
	@Override
	public PharmacyAdmin changePassword(String newPassword, PharmacyAdmin user) {
		
		 user.setPassword(newPassword);
	        if (user.getStatus().equals(UserStatus.NEVER_LOGGED_IN)) {
	            user.setStatus(UserStatus.ACTIVE);
	        }
	        return pharmacyAdminRepository.save(user);
		
	}
	
	@Override
	public List<PharmacyAdminDTO> getAllPharmacyAdminsForPharmacy(Long id) {
		
		List<PharmacyAdmin> pharmAdmins = pharmacyAdminRepository.findByPharmacyId(id);
		if(pharmAdmins == null) {
			return null;
		}
		
		return convertToDTO(pharmAdmins);
	}

	
	
	@Override
	public EditPharmAdminDTO getPharmacyAdmin(Long id) {
		
		PharmacyAdmin pharmAdmin = pharmacyAdminRepository.findOneById(id);
		
		return new EditPharmAdminDTO(pharmAdmin);
	}

	
	
	@Override
	public PharmacyAdminDTO create(PharmacyAdminDTO pharmacyAdminDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public PharmacyAdminDTO editPersonalInformation(@Valid EditPharmAdminDTO pharmacyAdminDTO) {
		
		PharmacyAdmin pharmacyAdmin = getLoginAdmin();
		
		if(pharmacyAdmin.getId() != pharmacyAdminDTO.getId()) {
			return null;
		}
		
		pharmacyAdmin.setFirstName(pharmacyAdminDTO.getFirstName());
		pharmacyAdmin.setLastName(pharmacyAdminDTO.getLastName());
		pharmacyAdmin.setPhoneNumber(pharmacyAdminDTO.getPhoneNumber());
		
		return new PharmacyAdminDTO(pharmacyAdminRepository.save(pharmacyAdmin));
	}

	
	
	
	public PharmacyAdmin getLoginAdmin() {
		
		 Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
	        try {
	        	//System.out.println(currentUser.getName());
	        	PharmacyAdmin pharmacyAdmin = pharmacyAdminRepository.findOneByUsername(currentUser.getName());
	            if (pharmacyAdmin != null) {
	                return pharmacyAdmin;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
	        return null;
	}



	private List<PharmacyAdminDTO> convertToDTO(List<PharmacyAdmin> adminsInPharmacy) {

		List<PharmacyAdminDTO> pharmAdminsDTO = new ArrayList<>();
		for(PharmacyAdmin pharmAdmin: adminsInPharmacy) {
			pharmAdminsDTO.add(new PharmacyAdminDTO(pharmAdmin));
		}
		
		return pharmAdminsDTO;
	}






	


}
