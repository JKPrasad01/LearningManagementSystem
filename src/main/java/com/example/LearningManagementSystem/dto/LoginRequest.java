package com.example.LearningManagementSystem.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private String userName; // username or email or contact
    private String password;
}
