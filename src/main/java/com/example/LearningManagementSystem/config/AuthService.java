package com.example.LearningManagementSystem.config;

import com.example.LearningManagementSystem.entity.UserEntity;
import com.example.LearningManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user details not found by username : "+username));
        return new AuthUser(user.getUsername(),user.getPassword(),user.getRole());

    }
}
