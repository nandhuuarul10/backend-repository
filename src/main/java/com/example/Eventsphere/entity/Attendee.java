package com.example.Eventsphere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="attendees")
public class Attendee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String fullName;
    private String email;
    @Enumerated(EnumType.STRING)
    private RegistrationType registrationType;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public RegistrationType getRegistrationType() {
        return registrationType;
    }
    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
