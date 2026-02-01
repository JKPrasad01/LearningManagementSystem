package com.example.LearningManagementSystem.repository;

import com.example.LearningManagementSystem.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModulesRepository extends JpaRepository<ModuleEntity, Long> {
}
