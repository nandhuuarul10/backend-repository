package com.example.Eventsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Eventsphere.entity.Session;

public interface SessionRepository extends JpaRepository<Session,Long> {

    
} 