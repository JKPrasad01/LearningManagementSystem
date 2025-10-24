package com.example.LearningManagementSystem.dto;

import com.example.LearningManagementSystem.enums.CourseLevel;
import com.example.LearningManagementSystem.enums.CourseStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDto {
    private Long courseId;

    @NotBlank(message = "course title is mandatory")
    private String title;

    @NotBlank(message = "course description is mandatory,provide description about course")
    private String description;

    @NotBlank(message = "course category is mandatory")
    private String category;

    @NotNull
    private CourseLevel courseLevel;

    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @Valid
    private List<ModulesDTO> modules;
    private List<EnrollmentDto> enrollments;
    private List<ReviewDto> reviews;

    @NotNull
    private CourseStatus courseStatus;
}
