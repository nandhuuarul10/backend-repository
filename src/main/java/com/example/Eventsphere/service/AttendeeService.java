package com.example.Eventsphere.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Eventsphere.dto.AttendeeDTO;
import com.example.Eventsphere.entity.Attendee;
import com.example.Eventsphere.entity.Session;
import com.example.Eventsphere.exception.EmailAlreadyExistsException;
import com.example.Eventsphere.exception.ResourceNotFoundException;
import com.example.Eventsphere.repository.AttendeeRepository;
import com.example.Eventsphere.repository.SessionRepository;


@Service
public class AttendeeService {
@Autowired
AttendeeRepository arepo;
    @Autowired
    SessionRepository srepo;

    private AttendeeDTO convertToDTO(Attendee atn) {
        AttendeeDTO attendeedto = new AttendeeDTO(atn.getFullName(), atn.getEmail(), atn.getRegistrationType());
        attendeedto.setId(atn.getId());
        if (atn.getSession() != null) {
            attendeedto.setSessionId(atn.getSession().getId());
        }
        return attendeedto;
    }

    public List<AttendeeDTO> getAllAttendees() {
      List<Attendee> at=arepo.findAll();
      List<AttendeeDTO> dto=new ArrayList<>();
      for(Attendee atn:at){
        dto.add(convertToDTO(atn));
      }
      return dto;
    }

    public Optional<AttendeeDTO> getAttendeeBYId(Long id) {
      Attendee s=arepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Attendee Not Found!"));
      return Optional.of(convertToDTO(s));
    }

    public AttendeeDTO addAttendee(AttendeeDTO attendeedto) {
      if(arepo.existsByEmail(attendeedto.getEmail())){
        throw new EmailAlreadyExistsException("Email Already exists!!");
      }

      Attendee a=new Attendee();
      a.setFullName(attendeedto.getFullName());
      a.setEmail(attendeedto.getEmail());
      a.setRegistrationType(attendeedto.getRegistrationType());
      if (attendeedto.getSessionId() != null) {
          Session s = srepo.findById(attendeedto.getSessionId()).orElseThrow(() -> new ResourceNotFoundException("Session Not Found"));
          a.setSession(s);
      }
      Attendee savedAttendee = arepo.save(a);
      return convertToDTO(savedAttendee);
    }

    public AttendeeDTO updateAttendee(Long id, AttendeeDTO updatedAttendee) {
      Attendee a=arepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found!!"));
      
      if (updatedAttendee.getEmail() != null && !updatedAttendee.getEmail().equalsIgnoreCase(a.getEmail())) {
          if (arepo.existsByEmail(updatedAttendee.getEmail())) {
              throw new EmailAlreadyExistsException("Email Already exists!!");
          }
      }

      a.setFullName(updatedAttendee.getFullName());
      a.setEmail(updatedAttendee.getEmail());
      a.setRegistrationType(updatedAttendee.getRegistrationType());
      if (updatedAttendee.getSessionId() != null) {
          Session s = srepo.findById(updatedAttendee.getSessionId()).orElseThrow(() -> new ResourceNotFoundException("Session Not Found"));
          a.setSession(s);
      } else {
          a.setSession(null);
      }
      Attendee savedAttendee = arepo.save(a);
      return convertToDTO(savedAttendee);
    }
    public String deleteAttendee(Long id) {

      arepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));
      arepo.deleteById(id);
      return "Attendee Deleted Successfully!!";

        
    }
      
    
}
