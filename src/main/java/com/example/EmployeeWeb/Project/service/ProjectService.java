package com.example.EmployeeWeb.Project.service;

import com.example.EmployeeWeb.Project.DTO.ProjectDTO;
import com.example.EmployeeWeb.Project.mapper.ProjectDTOMapper;
import com.example.EmployeeWeb.Project.model.Project;
import com.example.EmployeeWeb.Project.repository.ProjectRepository;
import com.example.EmployeeWeb.Project.validation.ProjectValidation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    private final ProjectRepository projectRepository;
    private final ProjectDTOMapper projectDTOMapper;
    private final ProjectValidation projectValidation;

    public ProjectService(ProjectRepository projectRepository, ProjectDTOMapper projectDTOMapper, ProjectValidation projectValidation) {
        this.projectRepository = projectRepository;
        this.projectDTOMapper = projectDTOMapper;
        this.projectValidation = projectValidation;
    }

    @Transactional
    public List<Project> findAll() {
        return projectRepository.findAll();
    }




    public Project getProjectByIdAsDTO(Long id) {
        return projectRepository.findByProjectId(id)
                .orElse(null);


    }


    @Transactional
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findByProjectId(id);
    }


    @Transactional
    public Project saveProject(Project project) throws Exception {
        ProjectDTO projectDTO = projectDTOMapper.toDTO(project);
        projectValidation.validateProject(projectDTO);
        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProjectById(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findByProjectId(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            projectRepository.delete(project);
        } else {
            throw new EntityNotFoundException("Project  with ID " + projectId + " not found");
        }
    }

    @Transactional
    public Project updateProject(Project project) throws Exception {
        Project existingProject = projectRepository.findById(project.getProjectId())
                .orElseThrow(() -> new Exception("Project not found"));
        ProjectDTO projectDTO = projectDTOMapper.toDTO(project);


        boolean isProjectNameChanged = !existingProject.getProjectName().equals(projectDTO.getProjectName());

        if (isProjectNameChanged) {
           throw  new ValidationException("You cannot change project name.");
        }
   // Project project= projectDTOMapper.toEntity(project);
    return projectRepository.saveAndFlush(project);
    }
}
