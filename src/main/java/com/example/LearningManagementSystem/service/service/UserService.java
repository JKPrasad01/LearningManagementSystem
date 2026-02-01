package com.example.LearningManagementSystem.service.service;

import com.example.LearningManagementSystem.authenticate.ApiResponse;
import com.example.LearningManagementSystem.dto.LoginRequest;
import com.example.LearningManagementSystem.dto.SignUpRequest;
import com.example.LearningManagementSystem.dto.UserDTO;
import com.example.LearningManagementSystem.dto.UserUpdateRequest;
import com.example.LearningManagementSystem.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    UserDTO saveUser(UserEntity user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO updateUser(Long id, UserUpdateRequest userUpdateRequest);
    void deleteUser(Long id);

    String signUpUser(SignUpRequest newUser);

    Map<String,Object> logInUser(LoginRequest loginRequestloginRequest, HttpServletResponse httpServletResponse);
}
