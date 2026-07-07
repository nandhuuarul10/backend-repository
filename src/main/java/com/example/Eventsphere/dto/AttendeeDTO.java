package com.example.Eventsphere.dto;

import com.example.Eventsphere.entity.RegistrationType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;




public class AttendeeDTO {
    private Long id;
    
   @NotBlank(message = "Attendee name is required!")
   private String fullName;
   @Email(message="email is not Valid!")
    private String email;  

    private RegistrationType registrationType;

    public AttendeeDTO() {}

    public AttendeeDTO(String fullName, String email, RegistrationType registrationType) {
        this.fullName = fullName;
        this.email = email;
        this.registrationType = registrationType;
    }

    private Long sessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
