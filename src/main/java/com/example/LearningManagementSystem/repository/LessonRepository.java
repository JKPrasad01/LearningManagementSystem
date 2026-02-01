package com.example.LearningManagementSystem.repository;

import com.example.LearningManagementSystem.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}
