package com.example.LearningManagementSystem.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;


    @CreationTimestamp
    private LocalDateTime issuedDate;

    private String certificationURL;

    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private CourseEntity course;

}
