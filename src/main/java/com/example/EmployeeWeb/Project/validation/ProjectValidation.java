package com.example.EmployeeWeb.Project.validation;

import com.example.EmployeeWeb.Project.dto.ProjectDTO;
import com.example.EmployeeWeb.Project.model.Project;
import com.example.EmployeeWeb.Project.repository.ProjectRepository;
import org.springframework.stereotype.Component;

@Component
public class ProjectValidation {
    private final ProjectRepository projectRepository;

    public ProjectValidation(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project validateProject(ProjectDTO projectDTO) throws Exception {
        if (isProjectNameExists(projectDTO.getProjectName())) {
            throw new Exception("Project name already exists.");
        }
        return null;
    }

    public boolean isProjectNameExists(String projectName) {
        return projectRepository.existsByProjectName(projectName);
    }
}