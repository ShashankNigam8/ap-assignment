package com.apassignemnt.ap.exception;

import java.util.UUID;

public class JwtValidationException extends RuntimeException {
    private String errorId;

    public JwtValidationException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

    public String getErrorId(){
        return this.errorId;
    }
}
