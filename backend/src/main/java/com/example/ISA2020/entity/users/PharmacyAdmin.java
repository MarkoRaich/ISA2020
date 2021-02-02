package com.example.ISA2020.entity.users;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.enumeration.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PharmacyAdmin implements UserDetails {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//username je email!
    @NotNull(message = "Username cannot be null.")
    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @NotNull(message = "Password cannot be null.")
    @Column(nullable = false)
    private String password;
    

	@Column(columnDefinition = "VARCHAR(30)", nullable = true)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(30)", nullable = true)
    private String lastName;
    
    @Column(columnDefinition = "VARCHAR(10)", unique = true, nullable = false)
    private String phoneNumber; 
    
    //status za proveru da li je ulogovan
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Pharmacy pharmacy;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "pharmacy_admin_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id") )
    private Set<Authority> authorities;
    
     
    public PharmacyAdmin() {
    	this.status = UserStatus.NEVER_LOGGED_IN;
    }
    
    
	public PharmacyAdmin(@NotNull(message = "Username cannot be null.") String username,
			@NotNull(message = "Password cannot be null.") String password, String firstName, String lastName,
			 String phoneNumber, Pharmacy pharmacy, Set<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.pharmacy = pharmacy;
		this.authorities = authorities;
		this.status = UserStatus.NEVER_LOGGED_IN;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

	@Override
	public String getPassword() { return this.password; }

	@Override
	public String getUsername() { return this.username; }

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { 
		return true; 
		//return (status != UserStatus.NEVER_LOGGED_IN);
	}
	
	//GETTERI I SETTERI----------------------------
    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}

    public Pharmacy getPharmacy() {
		return pharmacy;
	}


	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}


	public UserStatus getStatus() {
		return status;
	}


	public void setStatus(UserStatus status) {
		this.status = status;
	}


	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	

	
}
