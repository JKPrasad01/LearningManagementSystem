package com.example.LearningManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UserUpdateRequest {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "contact is required")
    private String contact;
}
