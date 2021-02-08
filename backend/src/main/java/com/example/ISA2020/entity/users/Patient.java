package com.example.ISA2020.entity.users;

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
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.entity.Complaint;
import com.example.ISA2020.entity.Consultation;
import com.example.ISA2020.entity.Drug;
import com.example.ISA2020.entity.Examination;
import com.example.ISA2020.entity.Grade;
import com.example.ISA2020.entity.Prescription;
import com.example.ISA2020.entity.Promotion;
import com.example.ISA2020.entity.Reservation;
import com.example.ISA2020.enumeration.CategoryStatus;
import com.example.ISA2020.enumeration.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

//REGISTROVANI KORISNIK - PACIJENT
@Table(name="patient") 
@Entity
public class Patient implements UserDetails {

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
    
    //status za proveru da li je ulogovan
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
    @Enumerated(EnumType.STRING)
    private CategoryStatus categoryStatus;


    //VEZE SA DRUGIM ENTITETIMA (TABELAMA U SMISLU BAZA)

    //lista lekova na koje je pacijent alergican
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "patient_alergie",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id") )
    private Set<Drug> alergies = new HashSet<>();
	
	//jedan pacijent moze da napravi vise zalbi
	@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<>();
	
	
	//Jedan pacijent moze da ima vise pregleda kod dermatologa
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<>();

	//Jedan pacijent moze da ima vise konsultacija kod farmaceuta
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Consultation> consultations = new HashSet<>();

	//lista promocija na koje se pacijent pretplatio
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "patient_promotion",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "promotion_id", referencedColumnName = "id") )
	private Set<Promotion> promotions = new HashSet<>();
	
	
	//Jedan pacijent moze da napravi vise rezervacija leka 
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Reservation> reservations = new HashSet<>();
	
	//Jedan pacijent moze da ima vise izdatih recepata
	@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions =  new HashSet<>();
	
	
	@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();


	//vezano za prava pristupa spring security
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "patient_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id") )
    private Set<Authority> authorities;




	//KONSTRUKTORI
    public Patient() { 
    	this.points = 0;
    	this.penalties = 0;
    	this.status = UserStatus.NEVER_LOGGED_IN;
    	this.categoryStatus = CategoryStatus.BEGINNER;
    }
	
    public Patient(@NotNull(message = "Username cannot be null.") String username,
			@NotNull(message = "Password cannot be null.") String password, String firstName, String lastName,
			String address, String city, String phoneNumber, Set<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.points = 0;
		this.penalties = 0;
		this.status = UserStatus.NEVER_LOGGED_IN;
		this.categoryStatus = CategoryStatus.BEGINNER;
		this.authorities = authorities;
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

	public Set<Drug> getAlergies() {
		return alergies;
	}

	public void setAlergies(Set<Drug> alergies) {
		this.alergies = alergies;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Set<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
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

	public Set<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(Set<Examination> examinations) {
		this.examinations = examinations;
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public CategoryStatus getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(CategoryStatus categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}
	
	
	
	
	
	
}
