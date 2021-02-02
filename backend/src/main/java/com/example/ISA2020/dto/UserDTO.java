package com.example.ISA2020.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDTO {
	
	//@Username(message = "Username is invalid.")
	@NotEmpty(message = "Username is empty.")
    private String username;

    @NotEmpty(message = "Old password empty.")
    private String oldPassword;

    @NotEmpty(message = "New password empty.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")
    private String newPassword;

    
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
    
}
