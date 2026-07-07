package com.example.Eventsphere.exception;
public class ResourceNotFoundException extends RuntimeException{
   public ResourceNotFoundException(String msg){
        super(msg);
    }
}