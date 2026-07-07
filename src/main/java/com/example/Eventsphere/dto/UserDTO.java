package com.example.Eventsphere.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
    @NotBlank(message = "Username is required!!")
     private String userName;
     @NotNull(message = "password must not be null")
    private String password;

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
   
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    
    
}
