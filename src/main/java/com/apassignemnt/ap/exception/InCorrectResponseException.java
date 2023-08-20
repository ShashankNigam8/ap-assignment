package com.apassignemnt.ap.exception;

import java.util.UUID;

public class InCorrectResponseException extends RuntimeException {
    private String errorId;

    public InCorrectResponseException(String message) {
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

    public String getErrorId(){
        return this.errorId;
    }
}
