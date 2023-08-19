package com.apassignemnt.ap.exception;

import com.apassignemnt.ap.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NoAuthException.class)
    public ResponseEntity<ErrorResponse> handleNoAuthException(NoAuthException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorId(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    //Global exception handler to catch any unhandled exception
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponse> handleGlobalException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID().toString(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
