package com.example.ISA2020.entity.users;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.Pharmacy;
import com.example.ISA2020.entity.VacationRequestDerm;
import com.example.ISA2020.entity.DermWorkHours;
import com.example.ISA2020.enumeration.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="dermatologist") 
@Entity
public class Dermatologist implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null.")
    @Column(nullable = false)
    private String username; //username je email!

    @JsonIgnore
    @NotNull(message = "Password cannot be null.")
    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(30)", nullable = true)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(30)", nullable = true)
    private String lastName;
    
    @Column(columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    private String phoneNumber;

//	@JsonFormat(pattern = "HH:mm")   NE MOZE OVAKO JER DERMATOLOG IMA RAZLICITO RADNO VEREME U RAZLICITIM APOTEKAMA
//	@NotNull
//	@Column(nullable = false)
//	private LocalTime workHoursFrom;
//
//	@JsonFormat(pattern = "HH:mm")
//	@NotNull
//	@Column(nullable = false)
//	private LocalTime workHoursTo;
    
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DermWorkHours> workHours;
	
	//prosecna ocena Dermatologa
	@Column
	private double rating;
	
	//status sa kojim proveravamo da li je ulogovan
	@Enumerated(EnumType.STRING)
    private UserStatus status;
    
    
    
   // @ManyToMany(mappedBy = "dermatologists", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   // private Set<Pharmacy> pharmacies;
    
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations= new HashSet<>();

    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VacationRequestDerm> vacationRequests = new HashSet<>();

	//vezano za prava pristupa spring security
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dermatologist_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;
	
	

	//KONSTRUKTORI
    public Dermatologist() {
    	this.rating = 0;
    	this.status = UserStatus.NEVER_LOGGED_IN;
    }
    
	public Dermatologist(@NotNull(message = "Username cannot be null.") String username,
			@NotNull(message = "Password cannot be null.") String password, String firstName, String lastName,
			String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rating = 0;
		this.status = UserStatus.NEVER_LOGGED_IN;
		this.phoneNumber = phoneNumber;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

    
   

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}


	public Set<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(Set<Examination> examinations) {
		this.examinations = examinations;
	}

	public Set<VacationRequestDerm> getVacationRequests() {
		return vacationRequests;
	}

	public void setVacationRequests(Set<VacationRequestDerm> vacationRequests) {
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
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
