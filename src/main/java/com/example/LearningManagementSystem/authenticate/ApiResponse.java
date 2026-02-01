package com.example.LearningManagementSystem.authenticate;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {

    private static HttpStatus httpStatus;
    private static Object data;
    private static String message;

}
