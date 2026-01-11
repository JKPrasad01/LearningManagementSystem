package com.example.LearningManagementSystem.config;


import com.example.LearningManagementSystem.dto.LoginRequest;
import com.example.LearningManagementSystem.dto.SignUpRequest;
import com.example.LearningManagementSystem.service.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody SignUpRequest newUser){
        String data= userService.signUpUser(newUser);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/log-in")
    public ResponseEntity<String> logInUSer(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        String token =  userService.logInUser(loginRequest,httpServletResponse);
        return ResponseEntity.ok(token);
    }



}
