package com.example.Eventsphere.controller;

import com.example.Eventsphere.service.JwtService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import com.example.Eventsphere.entity.SystemUser;
import com.example.Eventsphere.exception.UnauthorizedException;
import com.example.Eventsphere.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthService service;
    private final AuthenticationManager authenticationManager;
    private final com.example.Eventsphere.repository.UserRepository userRepository;

    public AuthController(AuthService service,AuthenticationManager authenticationManager, JwtService jwtService, com.example.Eventsphere.repository.UserRepository userRepository) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public SystemUser register(@Valid @RequestBody SystemUser user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody SystemUser user) {
       Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassWord()));

       if(!auth.isAuthenticated()){
        throw new UnauthorizedException("UserName and Password is Not Valid!!");
       }
            
       SystemUser dbUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new UnauthorizedException("User Not Valid!!"));

       return jwtService.generateToken(dbUser);
       }
    } 
    


    

