package com.example.eservices.exceptions;

public class NoDataFoundException extends  RuntimeException {
    public NoDataFoundException() {

        super("No data found");
    }
}
