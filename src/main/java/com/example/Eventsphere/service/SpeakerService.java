package com.example.Eventsphere.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Eventsphere.exception.EmailAlreadyExistsException;
import com.example.Eventsphere.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import com.example.Eventsphere.dto.SpeakerDTO;
import com.example.Eventsphere.entity.Speaker;
import com.example.Eventsphere.entity.Session;
import com.example.Eventsphere.repository.SpeakerRepository;
@Service
public class SpeakerService {
    @Autowired
    SpeakerRepository srepo;

    private SpeakerDTO convertToDTO(Speaker sp) {
        SpeakerDTO dto = new SpeakerDTO(sp.getName(), sp.getEmail(), sp.getExpertise(), sp.getBio());
        dto.setId(sp.getId());
        if (sp.getSessions() != null) {
            List<com.example.Eventsphere.dto.SessionDTO> sessionDTOs = new ArrayList<>();
            for (com.example.Eventsphere.entity.Session s : sp.getSessions()) {
                com.example.Eventsphere.dto.SessionDTO sd = new com.example.Eventsphere.dto.SessionDTO(
                    s.getSessionName(), s.getTopic(), s.getDayOfWeek(), s.getStartTime(), s.getEndTime(), s.getRoomNote()
                );
                sd.setId(s.getId());
                sessionDTOs.add(sd);
            }
            dto.setSessions(sessionDTOs);
        } else {
            dto.setSessions(new ArrayList<>());
        }
        return dto;
    }

    public List<SpeakerDTO> getAllSpeakers() {
        List<Speaker> speakers = srepo.findAll();
        List<SpeakerDTO> dt = new ArrayList<>();
        for (Speaker sp : speakers) {
            dt.add(convertToDTO(sp));
        }
        return dt;
    }

    public Optional<SpeakerDTO> getSpeakersById(Long id) {
        Speaker s = srepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("The id " + id + " is not Found!!"));
        return Optional.of(convertToDTO(s));
    }

    public SpeakerDTO createSpeaker(SpeakerDTO speakerDto) {
        if (srepo.existsByEmail(speakerDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Speaker speaker = new Speaker();
        speaker.setName(speakerDto.getName());
        speaker.setEmail(speakerDto.getEmail());
        speaker.setExpertise(speakerDto.getExpertise());
        speaker.setBio(speakerDto.getBio());

        Speaker saved = srepo.save(speaker);
        return convertToDTO(saved);
    }

    public SpeakerDTO updateSpeaker(Long id, SpeakerDTO updateddto) {
        Speaker s = srepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("the given id (" + id + ") is Not Found,unable to update!"));
        
        if (updateddto.getEmail() != null && !updateddto.getEmail().equalsIgnoreCase(s.getEmail())) {
            if (srepo.existsByEmail(updateddto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists");
            }
        }

        s.setName(updateddto.getName());
        s.setEmail(updateddto.getEmail());
        s.setExpertise(updateddto.getExpertise());
        s.setBio(updateddto.getBio());
        Speaker saved = srepo.save(s);
        
        return convertToDTO(saved);
    }

    @org.springframework.transaction.annotation.Transactional
    public String deleteSpeaker(Long id) {
        Speaker s = srepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Speaker Not Found!!"));
        if (s.getSessions() != null) {
            for (Session session : s.getSessions()) {
                session.setSpeaker(null);
            }
        }
        srepo.delete(s);
        return "Speaker Deleted Successfully!!";
    }
}
   

  
