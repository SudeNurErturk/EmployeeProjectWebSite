package com.example.EmployeeWeb.Project.service;

import com.example.EmployeeWeb.Project.mapper.ProjectDTOMapper;
import com.example.EmployeeWeb.Project.model.Project;
import com.example.EmployeeWeb.Project.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    private final ProjectRepository projectRepository;
    private final ProjectDTOMapper projectDTOMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectDTOMapper projectDTOMapper) {
        this.projectRepository = projectRepository;
        this.projectDTOMapper = projectDTOMapper;
    }
    @Transactional
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

//    public List<ProjectDtoRequest> getAllProjects() {
//
//        return projectRepository.findAll().stream()
//                .map(projectMapper::toDtoRequest)
//                .collect(Collectors.toList());
//
//
//    }

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
        Optional<Project> existingProject = projectRepository.findByProjectName(project.getProjectName());
        if (existingProject.isPresent() ) {
            throw new Exception("Project already exists");
        }
        //Project project = ProjectMapper.INSTANCE.toEntity(projectDTO);

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

}
