package com.example.LearningManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime enrolledAt;

    private Integer progress;  // percentage of completion

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private CourseEntity course;
}
