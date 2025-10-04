package com.example.LearningManagementSystem.repository;

import com.example.LearningManagementSystem.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
