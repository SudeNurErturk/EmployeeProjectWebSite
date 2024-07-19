package com.example.EmployeeWeb.Project.repository;

import com.example.EmployeeWeb.Project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByProjectId(Long projectId);
    Optional<Project> findByProjectName(String projectName);
}
