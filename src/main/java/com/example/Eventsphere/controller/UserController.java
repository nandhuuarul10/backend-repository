package com.example.Eventsphere.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Eventsphere.entity.SystemUser;
import com.example.Eventsphere.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userService; 

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/user")
    public ResponseEntity<List<SystemUser>> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<SystemUser> updateUser(
            @Valid @RequestBody SystemUser user,
            @PathVariable Long id,
            Authentication authentication) {
        return ResponseEntity.status(200).body(userService.updateUser(user, id, authentication));
    }
}
