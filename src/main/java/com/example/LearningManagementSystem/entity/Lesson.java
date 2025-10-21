package com.example.LearningManagementSystem.entity;

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

    private String contentUrl;   // Could be video link, PDF, etc.

    private String pdf;

    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Modules modules;
}
