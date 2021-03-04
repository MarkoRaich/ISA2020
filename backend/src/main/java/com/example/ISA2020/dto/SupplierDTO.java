package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;

import com.example.ISA2020.entity.users.Supplier;

public class SupplierDTO {
	
	//username je u stvari email
    @NotEmpty(message = "Username is empty.")
    private String username;

    @NotEmpty(message = "Password is empty.")
    private String password;
    
    @NotEmpty(message = "FirstName is empty.")
    private String firstName;
    
    @NotEmpty(message = "LastName is empty.")
    private String lastName;
    
    
    public SupplierDTO() {
    	super();
    }
    
    

	public SupplierDTO(@NotEmpty(message = "Username is empty.") String username,
			@NotEmpty(message = "Password is empty.") String password,
			@NotEmpty(message = "FirstName is empty.") String firstName,
			@NotEmpty(message = "LastName is empty.") String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public SupplierDTO(@NotEmpty(message = "Username is empty.") String username,
			@NotEmpty(message = "FirstName is empty.") String firstName,
			@NotEmpty(message = "LastName is empty.") String lastName) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	

	public SupplierDTO(Supplier supplier) {
		this.username = supplier.getUsername();
		this.firstName = supplier.getFirstName();
		this.lastName = supplier.getLastName();
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
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
    
    

}
