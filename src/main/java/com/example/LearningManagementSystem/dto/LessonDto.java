package com.example.LearningManagementSystem.dto;

import lombok.Data;

@Data
public class LessonDto {
    private Long lessonId;
    private String title;
    private String contentUrl;   // video/pdf url
    private String pdf;
    private Integer orderNumber;
}
