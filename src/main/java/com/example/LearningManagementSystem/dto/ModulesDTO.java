package com.example.LearningManagementSystem.dto;

import lombok.Data;
import java.util.List;

@Data
public class ModulesDTO {
    private Long chapterId;
    private String title;
    private Integer orderNumber;
    private List<LessonDto> lessons;
}
