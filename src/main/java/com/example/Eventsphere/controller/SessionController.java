package com.example.Eventsphere.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Eventsphere.dto.SessionDTO;
import com.example.Eventsphere.service.SessionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    @Autowired
    SessionService sessionService;
    
    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSessions(){
        List<SessionDTO> dto=sessionService.getAllSessions();
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable Long id) {
        Optional<SessionDTO> opt=sessionService.getSessionById(id);
        return ResponseEntity.status(200).body(opt.get());
    }

    @PostMapping
    public ResponseEntity<SessionDTO> addSession(@Valid @RequestBody SessionDTO sessiondto) {
        return ResponseEntity.status(201).body(sessionService.addSession(sessiondto));
    }
    
    @PutMapping("/UpdateSession/{id}")
    public ResponseEntity<SessionDTO> updateSession(@PathVariable Long id, @Valid @RequestBody SessionDTO updatedSession){
       return ResponseEntity.status(200).body(sessionService.updateSession(id, updatedSession));
    }
 
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/DeleteSession/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable Long id){
        return ResponseEntity.status(200).body(sessionService.deleteSession(id));
    }
}