package com.example.Eventsphere.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleStudent(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String,String>> handle(MethodArgumentNotValidException e){
            Map<String,String> error=new HashMap<>();
            e.getBindingResult().getFieldErrors().forEach(err->error.put(err.getField(),err.getDefaultMessage()));
            return ResponseEntity.status(400).body(error);
        }
    

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmail(EmailAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

     @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<?> handleUser(UserNameAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    
     @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUser(UnauthorizedException e){
        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  
}
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwb29qYWFAZ21haWwuY29tIiwidXNlcm5hbWUiOiJwb29qYXNyZWUiLCJyb2xlIjoiQURNSU4iLCJpYXQiOjE3ODI5OTMwODQsImV4cCI6MTc4Mzg1NzA4NH0.coaVO-QXq45e0tA0ICrSuNEH_YgVYry-r9O_ZF5kO2I
//eyJzdWIiOiJwb29qYWFAZ21haWwuY29tIiwidXNlcm5hbWUiOiJwb29qYXNyZWUiLCJyb2xlIjoiQURNSU4iLCJpYXQiOjE3ODI5OTMxMDMsImV4cCI6MTc4Mzg1NzEwM30









