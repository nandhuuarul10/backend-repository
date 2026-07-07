package com.example.Eventsphere.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Eventsphere.entity.SystemUser;
import com.example.Eventsphere.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SystemUser user = repo.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User Not Found !"));
        return User.builder()
               .username(user.getEmail())
               .password(user.getPassWord())
               .authorities("ROLE_"+user.getRole().name())
               .build();
    }
    
}

