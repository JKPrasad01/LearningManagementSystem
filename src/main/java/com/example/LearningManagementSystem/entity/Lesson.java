package com.example.LearningManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @Column(nullable = false)
    private String title;

    private String content;   // Could be video link, PDF, etc.

    private String pdf;

    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonBackReference
    private Modules modules;
}
