package com.example.LearningManagementSystem.service.serviceImpl;

import com.example.LearningManagementSystem.dto.UserDTO;
import com.example.LearningManagementSystem.entity.UserEntity;
import com.example.LearningManagementSystem.repository.UserRepository;
import com.example.LearningManagementSystem.service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public UserDTO saveUser(UserEntity user) {
        return modelMapper.map(userRepository.save(user),UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity,UserDTO.class)).toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity user= userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User details not found"));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
         UserEntity user= userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("User details not found"));
         return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, UserEntity userDetails) {
        UserEntity entity =userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setContact(userDetails.getContact());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));

        return modelMapper.map(entity,UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
