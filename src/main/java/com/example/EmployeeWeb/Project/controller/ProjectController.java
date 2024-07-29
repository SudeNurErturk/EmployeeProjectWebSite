package com.example.EmployeeWeb.Project.controller;

import com.example.EmployeeWeb.Project.dto.ProjectDTO;
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


    @GetMapping("/list")
    public ResponseEntity<?> getAllProjects() {
        List<Project> projects = projectService.findAll();
        List<ProjectDTO> projectDTOList = projectDTOMapper.toListDTO(projects);

        return new ResponseEntity<>(projectDTOList, HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping(value = "/addProject", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProject(@RequestBody ProjectDTO projectDTO) throws Exception {

        Project project = projectDTOMapper.toEntity(projectDTO);
        Project savedProject = projectService.saveProject(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }


    @PutMapping(value = "/update/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProject(@PathVariable Long projectId, @Valid @RequestBody ProjectDTO projectDto) throws Exception {

        Optional<Project> existingProject = projectService.getProjectById(projectId);
        if (existingProject.isPresent()) {
            projectDto.setProjectId(projectId);
            Project project = projectDTOMapper.toEntity(projectDto);
            Project updatedProject = projectService.updateProject(project);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        }

        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) throws Exception {

        Optional<Project> existingProject = projectService.getProjectById(id);

        if (existingProject.isPresent()) {
            projectService.deleteProjectById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Project deleted successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }

    }
}