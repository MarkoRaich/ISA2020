package com.example.ISA2020.entity.users;

import java.util.Collection;
import java.util.Set;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ISA2020.entity.Authority;
import com.example.ISA2020.enumeration.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SystemAdmin implements UserDetails {

	/**
	 * 
	 */
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
    
    @Column(columnDefinition = "VARCHAR(10)", unique = true, nullable = false)
    private String phoneNumber;
    
    //status za proveru da li je ulogovan
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
  //vezano za prava pristupa spring security
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "system_admin_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    
    
	//KONSTRUKTORI
	
	public SystemAdmin() {
		super();
		this.status = UserStatus.NEVER_LOGGED_IN;
	}

	public SystemAdmin(@NotNull(message = "Username cannot be null.") String username,
			@NotNull(message = "Password cannot be null.") String password, String firstName, String lastName,
			 UserStatus status) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = UserStatus.NEVER_LOGGED_IN;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//this.authorities = authorities;
		return null;
	}

	@Override
	public String getPassword() {
		//return this.authorities;
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
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
		return true;
	}

	@Override
	public boolean isEnabled() {
		return (status != UserStatus.NEVER_LOGGED_IN);
	}

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

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
