package com.example.LearningManagementSystem.config.oAuth;


import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class CustomOAuth2User {

    public String getEmail(OAuth2User oAuth2User){
        return oAuth2User.getAttribute("email");
    }


    public String getName(OAuth2User oAuth2User){
        return oAuth2User.getAttribute("name");
    }
}
