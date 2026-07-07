package com.example.Eventsphere.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Eventsphere.entity.SystemUser;


public interface UserRepository  extends JpaRepository<SystemUser,Long>{

    boolean existsByuserName(String userName);
    Optional<SystemUser> findByEmail(String Email);

    
}
