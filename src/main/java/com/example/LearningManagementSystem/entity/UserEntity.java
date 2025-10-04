package com.example.LearningManagementSystem.entity;


import com.example.LearningManagementSystem.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false,length = 50)
    private String username;

    @Column(unique = true,nullable = false ,length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    private String contact;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;
}
