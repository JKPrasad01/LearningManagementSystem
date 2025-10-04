package com.example.LearningManagementSystem.service.service;

import com.example.LearningManagementSystem.dto.CourseDto;
import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);
    CourseDto getCourseById(Long courseId);
    List<CourseDto> getAllCourses();
    CourseDto updateCourse(Long courseId, CourseDto courseDto);
    void deleteCourse(Long courseId);
}
