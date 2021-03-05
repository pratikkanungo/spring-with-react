package com.example.eservices.exceptions;

public class PasswordNotMatchException extends  RuntimeException {
    public PasswordNotMatchException() {

        super("Incorrect Password!");
    }
}
