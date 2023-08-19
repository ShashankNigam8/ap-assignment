package com.apassignemnt.ap.exception;

import java.util.UUID;

public class NoAuthException extends RuntimeException{
    private String errorId;

    public NoAuthException(String message){
        super(message);
        this.errorId = UUID.randomUUID().toString();
    }

    public String getErrorId(){
        return this.errorId;
    }
}
