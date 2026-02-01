package com.example.LearningManagementSystem.repository;

import com.example.LearningManagementSystem.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
}
