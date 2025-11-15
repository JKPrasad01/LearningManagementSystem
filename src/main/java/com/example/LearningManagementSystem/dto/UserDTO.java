package com.example.LearningManagementSystem.dto;

import com.example.LearningManagementSystem.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String contact;
    private Role role;
}
