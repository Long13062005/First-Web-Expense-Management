package com.example.expense_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotBlank(message = "Username can't be blank")
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can't have special characters")
    private String username;
    @NotBlank(message = "")
    @Size(min = 8,message = "The password must consist of at least 8 characters,include letters (uppercase) and digits")
    private String password;

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

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO() {
    }
}