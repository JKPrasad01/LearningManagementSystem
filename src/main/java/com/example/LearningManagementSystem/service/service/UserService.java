package com.example.LearningManagementSystem.service.service;

import com.example.LearningManagementSystem.dto.LoginRequest;
import com.example.LearningManagementSystem.dto.SignUpRequest;
import com.example.LearningManagementSystem.dto.UserDTO;
import com.example.LearningManagementSystem.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO saveUser(UserEntity user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO updateUser(Long id, UserEntity userDetails);
    void deleteUser(Long id);

    String signUpUser(SignUpRequest newUser);

    String logInUser(LoginRequest loginRequest);
}
