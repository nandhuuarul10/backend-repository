package com.example.Eventsphere.exception;

/**
 * UserNameAlreadyExistsException
 */
public class UserNameAlreadyExistsException extends RuntimeException {

    public UserNameAlreadyExistsException(String msg){
        super(msg);
    }
}
