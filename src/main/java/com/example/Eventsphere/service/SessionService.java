package com.example.Eventsphere.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Eventsphere.dto.SessionDTO;
import com.example.Eventsphere.dto.AttendeeDTO;
import com.example.Eventsphere.entity.Session;
import com.example.Eventsphere.entity.Speaker;
import com.example.Eventsphere.entity.Attendee;
import com.example.Eventsphere.exception.ResourceNotFoundException;
import com.example.Eventsphere.repository.SessionRepository;
import com.example.Eventsphere.repository.SpeakerRepository; 

@Service
public class SessionService {
    @Autowired
    SessionRepository srepo;

    @Autowired
    SpeakerRepository speakerRepo; 
    
    private SessionDTO convertToDTO(Session sn) {
        SessionDTO dto = new SessionDTO(sn.getSessionName(), sn.getTopic(), sn.getDayOfWeek(), sn.getStartTime(), sn.getEndTime(), sn.getRoomNote());
        dto.setId(sn.getId());
        if (sn.getSpeaker() != null) {
            dto.setSpeakerId(sn.getSpeaker().getId());
            dto.setSpeakerName(sn.getSpeaker().getName());
        }
        if (sn.getAttendees() != null) {
            List<AttendeeDTO> attendeesList = new ArrayList<>();
            for (Attendee a : sn.getAttendees()) {
                AttendeeDTO adto = new AttendeeDTO(a.getFullName(), a.getEmail(), a.getRegistrationType());
                adto.setId(a.getId());
                adto.setSessionId(sn.getId());
                attendeesList.add(adto);
            }
            dto.setAttendees(attendeesList);
        } else {
            dto.setAttendees(new ArrayList<>());
        }
        return dto;
    }

    public List<SessionDTO> getAllSessions() {
      List<Session> s=srepo.findAll();
      List<SessionDTO> dto=new ArrayList<>();
      for(Session sn:s){
        dto.add(convertToDTO(sn));
      }
      return dto;
    }

    public Optional<SessionDTO> getSessionById(Long id) {
      Session sn=srepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Session Not Found!"));
      return Optional.of(convertToDTO(sn));
    }

    public SessionDTO addSession(SessionDTO sessiondto) {
      Speaker speaker = null;
      if (sessiondto.getSpeakerId() != null) {
          speaker = speakerRepo.findById(sessiondto.getSpeakerId())
                  .orElseThrow(() -> new ResourceNotFoundException("Speaker Not Found!"));
      }
      
      Session a=new Session();
      a.setSessionName(sessiondto.getSessionName());
      a.setTopic(sessiondto.getTopic());
      a.setStartTime(sessiondto.getStartTime());
      a.setEndTime(sessiondto.getEndTime());
      a.setDayOfWeek(sessiondto.getDayOfWeek());
      a.setRoomNote(sessiondto.getRoomNote());
      if (speaker != null) {
          a.setSpeaker(speaker); 
      }
      
      Session saved = srepo.save(a);
      return convertToDTO(saved);
    }

    public SessionDTO updateSession(Long id, SessionDTO sessiondto) {
      Session a=srepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found!!"));
      
      Speaker speaker = null;
      if (sessiondto.getSpeakerId() != null) {
          speaker = speakerRepo.findById(sessiondto.getSpeakerId())
                  .orElseThrow(() -> new ResourceNotFoundException("Speaker Not Found!"));
      }
              
      a.setSessionName(sessiondto.getSessionName());
      a.setTopic(sessiondto.getTopic());
      a.setStartTime(sessiondto.getStartTime());
      a.setEndTime(sessiondto.getEndTime());
      a.setDayOfWeek(sessiondto.getDayOfWeek());
      a.setRoomNote(sessiondto.getRoomNote());
      a.setSpeaker(speaker); 
      
      Session saved = srepo.save(a);
      return convertToDTO(saved);
    }

  
    public String deleteSession(Long id) {
        Session session = srepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
        if (session.getAttendees() != null) {
            for (Attendee a : session.getAttendees()) {
                a.setSession(null);
            }
        }
        srepo.delete(session);
        return "Session Deleted Successfully!!";
    }
}