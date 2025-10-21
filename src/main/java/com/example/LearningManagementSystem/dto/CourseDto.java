package com.example.LearningManagementSystem.dto;

import com.example.LearningManagementSystem.enums.CourseLevel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDto {
    private Long courseId;
    private String title;
    private String description;
    private String category;
    private CourseLevel courseLevel;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private List<ModulesDTO> chapters;
    private List<EnrollmentDto> enrollments;
    private List<ReviewDto> reviews;
}
