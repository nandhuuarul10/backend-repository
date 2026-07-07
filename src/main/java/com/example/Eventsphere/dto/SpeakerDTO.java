package com.example.Eventsphere.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class SpeakerDTO{
    private Long id;
    
   @NotBlank(message = "name is required!")
  private String name;
  private String expertise;
  @Email(message = "email is not valid!!")
   private String email;
   private String bio;

   public SpeakerDTO() {}

   public SpeakerDTO( String name,String email, String expertise, String bio) {
    this.name = name;
    this.email= email;
    this.expertise = expertise;
    this.bio = bio;
   }

   public Long getId() {
       return id;
   }

   public void setId(Long id) {
       this.id = id;
   }
   public String getEmail() {
    return email;
   }
   public String getName() {
    return name;
   }
   public String getExpertise() {
    return expertise;
   }
    public String getBio() {
     return bio;
    }

    private java.util.List<SessionDTO> sessions;

    public java.util.List<SessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(java.util.List<SessionDTO> sessions) {
        this.sessions = sessions;
    }


 
}
