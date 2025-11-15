package com.example.LearningManagementSystem.config.oAuth;


import com.example.LearningManagementSystem.config.AuthUser;
import com.example.LearningManagementSystem.config.JwtUtil;

import com.example.LearningManagementSystem.entity.UserEntity;
import com.example.LearningManagementSystem.enums.Role;
import com.example.LearningManagementSystem.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final CustomOAuth2User customOAuth2User;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {


        OAuth2User auth2User=(OAuth2User) authentication.getPrincipal();

        String email=customOAuth2User.getEmail(auth2User);
        String name =customOAuth2User.getName(auth2User);


        Optional<UserEntity> existingUSer = userRepository.findByEmail(email);

        UserEntity user;

        if(existingUSer.isEmpty()){
            user = new UserEntity();
            user.setUsername(name);
            user.setEmail(email);
            user.setRole(Role.USER);
            userRepository.save(user);
        }else{
            user =existingUSer.get();
        }
        UserDetails userDetails= AuthUser.builder().username(user.getUsername()).role(user.getRole()).build();

        String accessToken =jwtUtil.generateToken(userDetails);
        String refreshToken=jwtUtil.generateRefreshToken(userDetails);


        Cookie accessCookie= new Cookie("accessToken",accessToken);
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false);
        accessCookie.setMaxAge(15*60);


        Cookie refreshCookie=new Cookie("refreshTOken",refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        response.sendRedirect("/http://localhost:8083/dashboard");
    }
}
