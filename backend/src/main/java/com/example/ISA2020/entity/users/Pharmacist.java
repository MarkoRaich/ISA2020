package com.example.ISA2020.entity.users;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.example.ISA2020.entity.Consultation;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.VacationRequestDerm;
import com.example.ISA2020.entity.VacationRequestPharm;
import com.example.ISA2020.enumeration.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="pharmacist") 
@Entity
public class Pharmacist implements UserDetails {


	private static final long serialVersionUID = 1L;

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

	@JsonFormat(pattern = "HH:mm")
	@NotNull
	@Column(nullable = false)
	private LocalTime workHourFrom;

	@JsonFormat(pattern = "HH:mm")
	@NotNull
	@Column(nullable = false)
	private LocalTime workHourTo;
	
	//prosecna ocena farmaceuta
	@Column
	private double rating;
	
    //status za proveru da li je ulogovan
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Pharmacy pharmacy;

	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Consultation> consultations = new HashSet<>();
	
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VacationRequestPharm> vacationRequests;

	//vezano za prava pristupa spring security
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pharmacist_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;
	

	
	//KONSTRUKTORI
    public Pharmacist() { 
    	this.rating = 0;
    	this.status = UserStatus.NEVER_LOGGED_IN;  	
    }
    
    
	public Pharmacist(@NotNull(message = "Username cannot be null.") String username,
			@NotNull(message = "Password cannot be null.") String password, String firstName, String lastName,
			@NotNull LocalTime workHourFrom, @NotNull LocalTime workHourTo, Pharmacy pharmacy,
			Set<Consultation> consultations, Set<VacationRequestPharm> vacationRequests, Set<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.workHourFrom = workHourFrom;
		this.workHourTo = workHourTo;
		this.rating = 0;
		this.pharmacy = pharmacy;
		this.consultations = consultations;
		this.vacationRequests = vacationRequests;
		this.authorities = authorities;
	}


	//GETERI I SETERI
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



	public Pharmacy getPharmacy() {
		return pharmacy;
	}



	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	

	public LocalTime getWorkHourFrom() {
		return workHourFrom;
	}


	public void setWorkHourFrom(LocalTime workHourFrom) {
		this.workHourFrom = workHourFrom;
	}


	public LocalTime getWorkHourTo() {
		return workHourTo;
	}


	public void setWorkHourTo(LocalTime workHourTo) {
		this.workHourTo = workHourTo;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public Set<Consultation> getConsultations() {
		return consultations;
	}


	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}


	public Set<VacationRequestPharm> getVacationRequests() {
		return vacationRequests;
	}


	public void setVacationRequests(Set<VacationRequestPharm> vacationRequests) {
		this.vacationRequests = vacationRequests;
	}


	public UserStatus getStatus() {
		return status;
	}


	public void setStatus(UserStatus status) {
		this.status = status;
	}


	//OVERRIDE METODE
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.getPassword();
	}

	@Override
	public String getUsername() {
    	return this.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
		//return (status != UserStatus.NEVER_LOGGED_IN);
	}

	
	

}
