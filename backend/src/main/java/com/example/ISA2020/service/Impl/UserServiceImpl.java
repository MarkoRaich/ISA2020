package com.example.ISA2020.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISA2020.dto.UserDTO;
import com.example.ISA2020.entity.users.Dermatologist;
import com.example.ISA2020.entity.users.Patient;
import com.example.ISA2020.entity.users.Pharmacist;
import com.example.ISA2020.entity.users.PharmacyAdmin;
import com.example.ISA2020.entity.users.Supplier;
import com.example.ISA2020.entity.users.SystemAdmin;
import com.example.ISA2020.enumeration.UserStatus;
import com.example.ISA2020.repository.DermatologistRepository;
import com.example.ISA2020.repository.PatientRepository;
import com.example.ISA2020.repository.PharmacistRepository;
import com.example.ISA2020.repository.PharmacyAdminRepository;
import com.example.ISA2020.repository.SupplierRepository;
import com.example.ISA2020.repository.SystemAdminRepository;
import com.example.ISA2020.service.PatientService;
import com.example.ISA2020.service.PharmacyAdminService;
import com.example.ISA2020.service.UserService;

//Izdvojen servis za sve korisnike aplikacije
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private PatientRepository patientRepository;
	 
	@Autowired
	private PharmacyAdminRepository pharmacyAdminRepository;
	
	@Autowired
	private SystemAdminRepository systemAdminRepository;
	
	
	@Autowired
	private DermatologistRepository dermatologistRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired 
	private PharmacyAdminService pharmacyAdminService;
	
	
	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetails userDetails = searchUserInAllRepositories(username);
		if (userDetails != null) {
            return userDetails;
        }
        throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
	}

	//trazi Usera u svim repozitorijumima
	@Override
	public UserDetails searchUserInAllRepositories(String username) {
		
		 try {
	            Patient patient = patientRepository.findOneByUsername(username);
	            if (patient != null) {
	                return patient;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 try {
	            PharmacyAdmin pharmacyAdmin = pharmacyAdminRepository.findOneByUsername(username);
	            if (pharmacyAdmin != null) {
	                return pharmacyAdmin;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            SystemAdmin systemAdmin = systemAdminRepository.findOneByUsername(username);
	            if (systemAdmin != null) {
	                return systemAdmin;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            Dermatologist dermatologist = dermatologistRepository.findOneByUsername(username);
	            if (dermatologist != null) {
	                return dermatologist;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            Pharmacist pharmacist = pharmacistRepository.findOneByUsername(username);
	            if (pharmacist != null) {
	                return pharmacist;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            Supplier supplier = supplierRepository.findOneByUsername(username);
	            if (supplier != null) {
	                return supplier;
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		return null;
	}
	//dopuni sa trazenjem po svim repozitorijumima case patient, case admin itd... -> dopunjeno

	@Override
	public UserDetails changePassword(UserDTO userDTO) {
		UserDetails user = searchUserInAllRepositories(userDTO.getUsername());
		
		if(user == null) {
			return null;
		}
		
		if(!passwordEncoder.matches(userDTO.getOldPassword(), user.getPassword())) {
			return null;
		}
		
		String newPassword = passwordEncoder.encode(userDTO.getNewPassword());
		
		if(user instanceof Patient) {
			return patientService.changePassword(newPassword, (Patient)user);
		}else if(user instanceof PharmacyAdmin) {
			//return pharmacyAdminService.changePassword(newPassword, (PharmacyAdmin)user);
		}else if(user instanceof Dermatologist) {
			
		}else if(user instanceof Pharmacist) {
			
		}else if(user instanceof SystemAdmin) {
			
		}else if(user instanceof Supplier) {
			
		}
		
		return null;
	}


	@Override
	public boolean neverLoggedIn(String username) {
		 try {
	            Patient patient = patientRepository.findOneByUsername(username);
	            if (patient != null) {
	            	return (patient.getStatus() == UserStatus.NEVER_LOGGED_IN);
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 try {
	            PharmacyAdmin pharmacyAdmin = pharmacyAdminRepository.findOneByUsername(username);
	            if (pharmacyAdmin != null) {
	                return (pharmacyAdmin.getStatus() == UserStatus.NEVER_LOGGED_IN);
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            SystemAdmin systemAdmin = systemAdminRepository.findOneByUsername(username);
	            if (systemAdmin != null) {
	            	return (systemAdmin.getStatus() == UserStatus.NEVER_LOGGED_IN);
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            Dermatologist dermatologist = dermatologistRepository.findOneByUsername(username);
	            if (dermatologist != null) {
	            	return (dermatologist.getStatus() == UserStatus.NEVER_LOGGED_IN);
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            Pharmacist pharmacist = pharmacistRepository.findOneByUsername(username);
	            if (pharmacist != null) {
	            	return (pharmacist.getStatus() == UserStatus.NEVER_LOGGED_IN);
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		 
		 try {
	            Supplier supplier = supplierRepository.findOneByUsername(username);
	            if (supplier != null) {
	            	return (supplier.getStatus() == UserStatus.NEVER_LOGGED_IN);
	            }
	        } catch (UsernameNotFoundException ex) {

	        }
		return false;
		
	}
	
	public UserDetails getLoggedInUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return searchUserInAllRepositories(currentUser.getName());
    }

	
	
}
