package com.example.Eventsphere.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.Eventsphere.entity.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker,Long>{

public boolean existsByEmail(String email);

}
