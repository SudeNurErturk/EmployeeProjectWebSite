package com.example.EmployeeWeb.Project.controller;

import com.example.EmployeeWeb.Project.DTO.ProjectDTO;
import com.example.EmployeeWeb.Project.mapper.ProjectDTOMapper;
import com.example.EmployeeWeb.Project.service.ProjectService;
import com.example.EmployeeWeb.Project.model.Project;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@RestController
@RequestMapping("/project")
public class ProjectController {


    private final ProjectService projectService;

    private final ProjectDTOMapper projectDTOMapper;


    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectByIdAsDTO(id);
        return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
    }
/*
    @GetMapping("/list")
    public List<ProjectDtoRequest> getAllProjects() {
        return projectService.getAllProjects();
    }*/

    @GetMapping("/list")
    public ResponseEntity getAllEmployees() {
        List<Project> projects = projectService.findAll();
        List<ProjectDTO> employeeDTORequests = projectDTOMapper.toListDTO(projects);
        return new ResponseEntity<>(employeeDTORequests, HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping(value = "/addProject", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProject(@RequestBody ProjectDTO projectDTO) {
        try {
            Project project = projectDTOMapper.toEntity(projectDTO);
            Project savedProject = projectService.saveProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping(value = "/update/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @Valid @RequestBody ProjectDTO projectDto) {
        try {
            Optional<Project> existingProject = projectService.getProjectById(projectId);
            if (existingProject.isPresent()) {

                projectDto.setProjectId(projectId);
                // project.setProjectName(project.getProjectName());
                Project project = projectDTOMapper.toEntity(projectDto);
                Project updatedProject = projectService.updateProject(project);
//                ProjectDtoRequest projectDtoRequest = projectMapper.toDtoRequest(updatedProject);
                return new ResponseEntity<>(updatedProject, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
            }
        }
            catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
          catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {

        try {
            Optional<Project> existingProject = projectService.getProjectById(id);
            if (existingProject.isPresent()) {
                projectService.deleteProjectById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Project deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}