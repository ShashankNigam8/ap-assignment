package com.apassignemnt.ap.exception;

import java.util.UUID;

public class NoCountryFoundException extends RuntimeException {
    private String errorId;

    public NoCountryFoundException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

    public String getErrorId(){
        return this.errorId;
    }
}
