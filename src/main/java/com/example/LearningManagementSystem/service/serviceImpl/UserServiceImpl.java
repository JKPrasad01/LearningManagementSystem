package com.example.LearningManagementSystem.service.serviceImpl;

import com.example.LearningManagementSystem.authenticate.ApiResponse;
import com.example.LearningManagementSystem.authenticate.AuthService;
import com.example.LearningManagementSystem.authenticate.AuthUser;
import com.example.LearningManagementSystem.utils.JwtUtil;
import com.example.LearningManagementSystem.dto.LoginRequest;
import com.example.LearningManagementSystem.dto.SignUpRequest;
import com.example.LearningManagementSystem.dto.UserDTO;
import com.example.LearningManagementSystem.dto.UserUpdateRequest;
import com.example.LearningManagementSystem.entity.UserEntity;
import com.example.LearningManagementSystem.enums.Role;
import com.example.LearningManagementSystem.exception.UserDetailsNotFoundException;
import com.example.LearningManagementSystem.repository.UserRepository;
import com.example.LearningManagementSystem.service.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
           throw new UserDetailsNotFoundException("User email are already exists " + newUser.getEmail(), HttpStatus.CONFLICT);
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
    public Map<String,Object> logInUser(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
            throw new UserDetailsNotFoundException("Username or email or contact is required",HttpStatus.NOT_FOUND);
        }

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new UserDetailsNotFoundException("Password is required",HttpStatus.NOT_FOUND);
        }

        UserEntity user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()->new UsernameNotFoundException("user not found by : "+loginRequest.getUsername()));

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new UserDetailsNotFoundException("Invalid Password",HttpStatus.NOT_FOUND);
        }

        UserDetails userDetails= AuthUser.builder().username(user.getUsername()).role(user.getRole()).build();


        String authToken=jwtUtil.generateToken(userDetails);
        String refreshToken= jwtUtil.generateRefreshToken(userDetails);

        Cookie authCookie=new Cookie("accessToken",authToken);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(false);
        authCookie.setMaxAge(15*60);
        authCookie.setPath("/");

        Cookie refreshCookie=new Cookie("refreshToken",refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);

        httpServletResponse.addCookie(authCookie);
        httpServletResponse.addCookie(refreshCookie);

        Map<String,Object> res=new HashMap<>();
        res.put("user",userDetails);
        res.put("accessToken",authToken);
        res.put("refreshToken",refreshToken);

        return res;
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
    public UserDTO updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        UserEntity entity =userRepository.findById(id).map(user -> {
            user.setUsername(userUpdateRequest.getUsername());
            user.setContact(userUpdateRequest.getContact());
            return userRepository.save(user);
        }).orElseThrow(() -> new UsernameNotFoundException("User not found with id " + id));

        return modelMapper.map(entity,UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
