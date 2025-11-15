package com.example.LearningManagementSystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private LocalDateTime localDateTime=LocalDateTime.now();

}
