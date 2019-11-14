package com.cramstack.contact_management.Payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ContactPayload {

    @NotBlank(message = "Enter your first name")
    private String firstName;

    @NotBlank(message = "Enter your last name")
    private String lastName;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Enter your email")
    private String email;

    @NotBlank(message = "Enter your phone number")
    private String phoneNumber;

    @NotBlank(message = "Enter your address")
    private String address;

    public ContactPayload() {
    }

    public ContactPayload(String firstName, String lastName,  String email, String phoneNumber,String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
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
}
