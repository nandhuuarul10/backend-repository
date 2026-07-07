package com.example.Eventsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Eventsphere.entity.SystemUser;
import com.example.Eventsphere.exception.EmailAlreadyExistsException;
import com.example.Eventsphere.exception.UserNameAlreadyExistsException;
import com.example.Eventsphere.repository.UserRepository;

@Service
public class AuthService {
 
     @Autowired
     PasswordEncoder encoder;
     @Autowired
      UserRepository repo;
    public SystemUser register(SystemUser user)
    {
     if(repo.existsByuserName(user.getUserName()))
        {
        throw new UserNameAlreadyExistsException("UserName Already Exists,Please choose a unique UserName!");
        }
     
     if(repo.findByEmail(user.getEmail()).isPresent())
        {
        throw new EmailAlreadyExistsException("Email Already Exists,Please choose a unique Email!");
        }

     SystemUser a=new SystemUser();
     a.setUserName(user.getUserName());
     a.setPassWord(encoder.encode(user.getPassWord()));
     a.setRole(user.getRole());
     a.setEmail(user.getEmail());

     repo.save(a);

     return a;

    }
}
