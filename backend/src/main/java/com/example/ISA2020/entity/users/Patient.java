package com.example.ISA2020.entity.users;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.Examination;
import com.fasterxml.jackson.annotation.JsonIgnore;

//REGISTROVANI KORISNIK - PACIJENT
@Table(name="patient") 
@Entity
public class Patient implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//username je u stvari email
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

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String address;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String city;
    
    @Column(columnDefinition = "VARCHAR(10)", unique = true, nullable = false)
    private String phoneNumber;
    
    @Column
    private int points;
    
    @Column
    private int penalties;
    
   
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "patient_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id") )
    private Set<Authority> authorities;
	
	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(mappedBy = "patient") private Set<Examination> examinations;
	 */
	
	/*
	 * private Set<Examination> bookedExaminations;
	 * 
	 * private Set<Examination> examinations;
	 */
    
    //DODATNI ATRIBUTI STATUS MEDICAL RECORD LISTA PREGLEDA ITD...
	
    public Patient() {}
	
    public Patient(@NotNull(message = "Username cannot be null.") String username,
			@NotNull(message = "Password cannot be null.") String password, String firstName, String lastName,
			String address, String city, String phoneNumber, Set<Authority> authorities, int points, int penalties) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.authorities = authorities;
		this.points = points;
		this.penalties = penalties;
		//this.examinations = null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { return this.authorities;	}

	@Override
	public String getPassword() { return this.password; }

	@Override
	public String getUsername() { return this.username;	}

	@Override
	public boolean isAccountNonExpired() {	return true; }

	@Override
	public boolean isAccountNonLocked() { return true;	}

	@Override
	public boolean isCredentialsNonExpired() { return true;	}

	@Override
	public boolean isEnabled() { return true; }

	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

//	public Set<Examination> getExaminations() {
//		return examinations;
//	}
//
//	public void setExaminations(Set<Examination> examinations) {
//		this.examinations = examinations;
//	}
	
	

}
