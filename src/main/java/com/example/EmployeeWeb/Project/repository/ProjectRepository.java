package com.example.EmployeeWeb.Project.repository;

import com.example.EmployeeWeb.Project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByProjectId(Long projectId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Project p WHERE p.projectName = :projectName")
    boolean existsByProjectName(@Param("projectName") String projectName);
}
