package com.example.LearningManagementSystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationId;

    @Column(nullable = false,unique = true)
    private Long userId;

    @Column(nullable = false,updatable = false)
    private Long courseId;

    @CreationTimestamp
    private LocalDateTime issuedDate;

    private String certificationURL;

}
