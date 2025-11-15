package com.example.LearningManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;


    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private CourseEntity course;

    private Integer rating;

    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
