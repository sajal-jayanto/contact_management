package com.cramstack.contact_management.Payloads;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserPayload {

    @NotBlank(message = "User name is required!")
    private String username;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Length(min = 5 , message = "Your Password must be at least 5 characters long")
    @NotBlank(message = "Password is required")
    private String password;

    @Length(min = 5 , message = "Your Password must be at least 5 characters long")
    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;

    public UserPayload() {
    }

    public UserPayload(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserPayload(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
