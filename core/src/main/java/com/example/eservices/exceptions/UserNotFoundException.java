package com.example.eservices.exceptions;

public class UserNotFoundException extends  RuntimeException {
    public UserNotFoundException(String username) {

        super(String.format("User with name %s not found", username));
    }

}
