package com.example.LearningManagementSystem.exception;


import org.springframework.http.HttpStatus;

public class UserDetailsNotFoundException extends RuntimeException {
    public  static HttpStatus status;
    public UserDetailsNotFoundException(String message, HttpStatus status) {
        super(message);
        UserDetailsNotFoundException.status =status;
    }
}
