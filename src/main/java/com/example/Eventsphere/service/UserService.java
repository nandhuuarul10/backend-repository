package com.example.Eventsphere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Eventsphere.entity.SystemUser;
import com.example.Eventsphere.exception.EmailAlreadyExistsException;
import com.example.Eventsphere.exception.ResourceNotFoundException;
import com.example.Eventsphere.exception.UnauthorizedException;
import com.example.Eventsphere.exception.UserNameAlreadyExistsException;
import com.example.Eventsphere.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository urepo;
    @Autowired
    PasswordEncoder encoder;

    public List<SystemUser> getAllUsers() {
        return urepo.findAll();
    }

    public SystemUser updateUser(SystemUser user, Long id, Authentication authentication) {
        SystemUser a = urepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!!"));
        
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        boolean isOwner = a.getEmail().equalsIgnoreCase(authentication.getName());

        if (!isAdmin && !isOwner) {
            throw new UnauthorizedException("You are not authorized to update this profile!");
        }

        // Only Admin can change roles
        if (user.getRole() != null) {
            if (isAdmin) {
                a.setRole(user.getRole());
            } else if (user.getRole() != a.getRole()) {
                throw new UnauthorizedException("Only admins can modify roles!");
            }
        }

        // Validate unique username if changed
        if (user.getUserName() != null && !user.getUserName().equals(a.getUserName())) {
            if (urepo.existsByuserName(user.getUserName())) {
                throw new UserNameAlreadyExistsException("UserName Already Exists,Please choose a unique UserName!");
            }
            a.setUserName(user.getUserName());
        }

        // Validate unique email if changed
        if (user.getEmail() != null && !user.getEmail().equalsIgnoreCase(a.getEmail())) {
            if (urepo.findByEmail(user.getEmail()).isPresent()) {
                throw new EmailAlreadyExistsException("Email Already Exists,Please choose a unique Email!");
            }
            a.setEmail(user.getEmail());
        }

        // Only update password if a new one is provided
        if (user.getPassWord() != null && !user.getPassWord().isEmpty()) {
            a.setPassWord(encoder.encode(user.getPassWord()));
        }

        return urepo.save(a);
    }
}
