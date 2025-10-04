package com.example.LearningManagementSystem.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class JwtUtil {
    private final String SECRET_KEY = "secret123";  // move to env var
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour





}

