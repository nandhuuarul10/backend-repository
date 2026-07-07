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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Eventsphere.dto.AttendeeDTO;
import com.example.Eventsphere.service.AttendeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {
    @Autowired
    AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<List<AttendeeDTO>> getAllAttendees(){
        List<AttendeeDTO> dto=attendeeService.getAllAttendees();
        return ResponseEntity.status(200).body(dto);
        
    }
    @GetMapping("/GetById/{id}")
    public ResponseEntity<AttendeeDTO> getAttendeeById(@PathVariable Long id)
{
    Optional<AttendeeDTO> opt=attendeeService.getAttendeeBYId(id);
    return ResponseEntity.status(200).body(opt.get());
}
    @PostMapping
    public ResponseEntity<AttendeeDTO> addAttendee(@Valid @RequestBody AttendeeDTO attendeedto)
    {
        return ResponseEntity.status(201).body(attendeeService.addAttendee(attendeedto));
    }
    
     @PutMapping("/UpdateAttendee/{id}")
    public ResponseEntity<AttendeeDTO> updateAttendee(@PathVariable Long id,@Valid @RequestBody AttendeeDTO updatedAttendee){
       return  ResponseEntity.status(200).body(attendeeService.updateAttendee(id,updatedAttendee));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/DeleteAttendee/{id}")
    public ResponseEntity<String> deleteAttendee(@PathVariable Long id){
        return ResponseEntity.status(200).body(attendeeService.deleteAttendee(id));
    }



}
