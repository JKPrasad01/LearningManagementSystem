package com.example.LearningManagementSystem.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterId;

    @Column(nullable = false)
    private String title;

    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Lesson> lessons;
}
