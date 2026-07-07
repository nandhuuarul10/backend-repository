package com.example.Eventsphere.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Eventsphere.dto.SpeakerDTO;
import com.example.Eventsphere.service.SpeakerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/speakers")
public class SpeakerController {
@Autowired
SpeakerService speakerservice;

    @GetMapping
    public ResponseEntity <List<SpeakerDTO>> getAllSpeakers(){
       return ResponseEntity.status(200).body(speakerservice.getAllSpeakers());
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeakerDTO> getSpeakersById(@PathVariable Long id){
        Optional<SpeakerDTO> dto=speakerservice.getSpeakersById(id);
        return ResponseEntity.status(200).body(dto.get());

    }
    
        @PostMapping
    public ResponseEntity<SpeakerDTO> createSpeaker(@Valid @RequestBody SpeakerDTO speakerDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(speakerservice.createSpeaker(speakerDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SpeakerDTO> updateSpeaker(@PathVariable Long id,@Valid@RequestBody SpeakerDTO updateddto){
        return ResponseEntity.status(200).body(speakerservice.updateSpeaker(id,updateddto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpeaker(@PathVariable long id){
       String str=speakerservice.deleteSpeaker(id);
        return ResponseEntity.status(200).body(str);
    }
   
}
