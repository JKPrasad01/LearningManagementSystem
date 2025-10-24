package com.example.LearningManagementSystem.entity;


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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Lesson> lessons;
}
