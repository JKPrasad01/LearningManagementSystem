package com.example.LearningManagementSystem.service.serviceImpl;

import com.example.LearningManagementSystem.config.AuthService;
import com.example.LearningManagementSystem.config.AuthUser;
import com.example.LearningManagementSystem.config.JwtUtil;
import com.example.LearningManagementSystem.dto.LoginRequest;
import com.example.LearningManagementSystem.dto.SignUpRequest;
import com.example.LearningManagementSystem.dto.UserDTO;
import com.example.LearningManagementSystem.entity.UserEntity;
import com.example.LearningManagementSystem.enums.Role;
import com.example.LearningManagementSystem.exception.UserDetailsNotFoundException;
import com.example.LearningManagementSystem.repository.UserRepository;
import com.example.LearningManagementSystem.service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String signUpUser(SignUpRequest newUser){

       boolean found=userRepository.existsByEmail(newUser.getEmail());

       if(found){
           throw new UserDetailsNotFoundException("User email are already exists " + newUser.getEmail());
       }
        UserEntity user=new UserEntity();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return "Register Successful";
    }

    @Override
    public String logInUser(LoginRequest loginRequest) {
        if (loginRequest.getUserName() == null || loginRequest.getUserName().trim().isEmpty()) {
            throw new UserDetailsNotFoundException("Username or email or contact is required");
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            throw new UserDetailsNotFoundException("Password is required");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
        );

        final UserDetails userDetails = authService.loadUserByUsername(loginRequest.getUserName());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new UserDetailsNotFoundException("Invalid credentials");
        }

        return jwtUtil.generateToken(userDetails);
    }


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
        UserEntity user= userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User details not found"));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
         UserEntity user= userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User details not found"));
         return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, UserEntity userDetails) {
        UserEntity entity =userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setContact(userDetails.getContact());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new UsernameNotFoundException("User not found with id " + id));

        return modelMapper.map(entity,UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
