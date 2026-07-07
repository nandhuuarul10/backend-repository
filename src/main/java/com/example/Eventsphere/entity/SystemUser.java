package com.example.Eventsphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SystemUser {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
 @Column(unique =true)
    private String userName;
    private String passWord;
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Enumerated(EnumType.STRING)
    private Role  role;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
public enum Role{
    ADMIN,
    USER
}

public Role getRole() {
    return role;
}
public void setRole(Role role) {
    this.role = role;
}
    
    
}
