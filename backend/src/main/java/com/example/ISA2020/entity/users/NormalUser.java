package com.example.ISA2020.entity.users;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.dto.NormalUserDTO;
import com.example.ISA2020.entity.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NormalUser implements UserDetails {
// Deni probao test klasa treba obrisati
	
	
	
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    //USERNAME JE U STVARI EMAIL!! ZBOG JEDNOSTAVNOSTI LOGINA OSTAVITI
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

    @Email()
    private String email;

    @Column(columnDefinition = "VARCHAR(50)", nullable = true)
    private String country;

    @Column(columnDefinition = "VARCHAR(50)", nullable = true)
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(50)", nullable = true)
    private String address;

    @Column(columnDefinition = "VARCHAR(50)", nullable = true)
    private String city;
    
    
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "normal_user_authority",
            joinColumns = @JoinColumn(name = "normal_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;
    
    
    

	public NormalUser() {
		super();
	}


	public NormalUser(Long id,
			@NotEmpty(message = "Username cannot be empty.") @NotNull(message = "Username cannot be null.") String username,
			@NotEmpty(message = "Password cannot be empty.") @NotNull(message = "Password cannot be null.") String password,
			String firstName, String lastName, @Email String email, String country, String phoneNumber, String address,
			String city) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
	}
	
	
	public NormalUser(
			@NotNull(message = "Username cannot be null.") String username,
			@NotNull(message = "Password cannot be null.") String password,
			String firstName, String lastName, @Email String email, String country, String phoneNumber, String address,
			String city) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
	}
	
	public NormalUser(NormalUserDTO userDTO) {
		this(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
				userDTO.getCountry(), userDTO.getPhoneNumber(), userDTO.getAddress(), userDTO.getCity());
	}
	
	




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setUsername(String username) {
		this.username = username;
	}




	public void setPassword(String password) {
		this.password = password;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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


	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
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
	}

	
    
}
