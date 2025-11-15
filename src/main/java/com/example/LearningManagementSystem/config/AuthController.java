package com.example.LearningManagementSystem.config;


import com.example.LearningManagementSystem.dto.LoginRequest;
import com.example.LearningManagementSystem.dto.SignUpRequest;
import com.example.LearningManagementSystem.service.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;



    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody @Validated SignUpRequest newUser) {
        String data = userService.signUpUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies=request.getCookies();

        if(cookies == null){
            return new ResponseEntity<>("No refresh token found", HttpStatus.UNAUTHORIZED);
        }
        String refreshToken = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refreshToken")){
                refreshToken=cookie.getValue();
            }
        }

        if(refreshToken==null)return new ResponseEntity<>("refresh token is missing",HttpStatus.UNAUTHORIZED);


        String username = jwtUtil.extractUserName(refreshToken);

        UserDetails userDetails=userDetailsService.loadUserByUsername(username);

        if(!jwtUtil.isTokenValid(refreshToken,userDetails)){
            return new ResponseEntity<>("Invalid refresh Token ",HttpStatus.UNAUTHORIZED);
        }
        String newAccessToken = jwtUtil.generateToken(userDetails);


        Cookie cookie=new Cookie("accessToken", newAccessToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(15 * 60);

        response.addCookie(cookie);

        return ResponseEntity.ok("Token Refreshed");
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logoutExistingUser(HttpServletResponse response){

        Cookie access = new Cookie("accessToken", "");
        access.setHttpOnly(true);
        access.setSecure(false);
        access.setPath("/");
        access.setMaxAge(0);

        Cookie refresh = new Cookie("refreshToken", "");
        refresh.setHttpOnly(true);
        refresh.setSecure(false);
        refresh.setPath("/");
        refresh.setMaxAge(0);

        response.addCookie(access);
        response.addCookie(refresh);
        response.addCookie(access);
        response.addCookie(refresh);

        return ResponseEntity.ok("Logged Out");
    }
}
