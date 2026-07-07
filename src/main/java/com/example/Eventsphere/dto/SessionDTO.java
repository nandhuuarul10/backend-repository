package com.example.Eventsphere.dto;

import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class SessionDTO {
    private Long id;
    
    @NotBlank(message = "Session name is required!")
    private String sessionName;
    private String topic;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomNote;

    public SessionDTO() {}

    public SessionDTO(String sessionName, String topic, String dayOfWeek,
                      LocalTime startTime, LocalTime endTime, String roomNote) {
        this.sessionName = sessionName;
        this.topic = topic;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNote = roomNote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getRoomNote() {
        return roomNote;
    }

    public void setRoomNote(String roomNote) {
        this.roomNote = roomNote;
    }

    private List<AttendeeDTO> attendees;

    public List<AttendeeDTO> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<AttendeeDTO> attendees) {
        this.attendees = attendees;
    }

    private Long speakerId;
    private String speakerName;

    public Long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Long speakerId) {
        this.speakerId = speakerId;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }
}

