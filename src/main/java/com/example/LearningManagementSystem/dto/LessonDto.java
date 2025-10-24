package com.example.LearningManagementSystem.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LessonDto {
    private Long lessonId;

    @NotBlank(message = "lesson title is mandatory")
    private String title;
    private String content;   // video/pdf url
    private String pdf;
    private Integer orderNumber;
}
