package com.example.LearningManagementSystem.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduleId;

    @Column(nullable = false)
    private String title;

    private Integer orderNumber;

    @OneToMany(mappedBy = "modules", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Lesson> lessons;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private CourseEntity course;
}
