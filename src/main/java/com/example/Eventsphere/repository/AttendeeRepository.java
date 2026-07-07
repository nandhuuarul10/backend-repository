package com.example.Eventsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Eventsphere.entity.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee,Long> 
{
    public boolean  existsByEmail(String email);
}
