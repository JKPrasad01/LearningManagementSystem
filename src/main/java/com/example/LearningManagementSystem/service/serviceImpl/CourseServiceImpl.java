package com.example.LearningManagementSystem.service.serviceImpl;

import com.example.LearningManagementSystem.dto.CourseDto;
import com.example.LearningManagementSystem.entity.CourseEntity;
import com.example.LearningManagementSystem.repository.CourseRepository;

import com.example.LearningManagementSystem.service.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        CourseEntity courseEntity = modelMapper.map(courseDto, CourseEntity.class);
        CourseEntity savedCourse = courseRepository.save(courseEntity);
        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return modelMapper.map(courseEntity, CourseDto.class);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<CourseEntity> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courseEntity.setTitle(courseDto.getTitle());
        courseEntity.setDescription(courseDto.getDescription());
        courseEntity.setCategory(courseDto.getCategory());
        courseEntity.setCourseLevel(courseDto.getCourseLevel());
        courseEntity.setPrice(courseDto.getPrice());

        CourseEntity updatedCourse = courseRepository.save(courseEntity);
        return modelMapper.map(updatedCourse, CourseDto.class);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
