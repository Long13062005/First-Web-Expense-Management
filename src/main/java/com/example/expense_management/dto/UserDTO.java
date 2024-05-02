package com.example.expense_management.dto;

import com.example.expense_management.model.User;
import jakarta.validation.constraints.*;

public class UserDTO {
    private Integer id;

    @NotBlank(message = "Need to fill this blank")
    private String firstName;
    @NotBlank(message = "Need to fill this blank")

    private String lastName;

    @Email
    @NotBlank(message = "Need to fill this blank")
    private String email;
    @Pattern(regexp = "^0\\d{9}$",message = "Phone number only have 10 numbers")
    private String phoneNumber;

    @NotBlank(message = "")
    @Size(min = 8,message = "The password must consist of at least 8 characters,include letters (uppercase) and digits")
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public UserDTO() {
    }
}
