package com.example.Eventsphere.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name="speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    Long id;
    @NotBlank
    String name;
    @Column(unique=true)
    String email;
    String expertise;
    String bio;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getExpertise() {
        return expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    @jakarta.persistence.OneToMany(mappedBy = "speaker", cascade = {jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.MERGE})
    private java.util.List<Session> sessions = new java.util.ArrayList<>();

    public java.util.List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(java.util.List<Session> sessions) {
        this.sessions = sessions;
    }

    @jakarta.persistence.PreRemove
    private void preRemove() {
        if (sessions != null) {
            for (Session s : sessions) {
                s.setSpeaker(null);
            }
        }
    }
}
