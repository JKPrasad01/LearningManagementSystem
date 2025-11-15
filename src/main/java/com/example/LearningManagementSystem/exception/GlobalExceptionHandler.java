package com.example.LearningManagementSystem.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserDetailsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerForUserDetailsNotFoundException(UserDetailsNotFoundException userDetailsNotFoundException){
        ErrorResponse response=new ErrorResponse();
        response.setMessage(userDetailsNotFoundException.getMessage());
        response.setStatus(UserDetailsNotFoundException.status);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
