package com.example.EmployeeWeb.Project.mapper;

import com.example.EmployeeWeb.Project.dto.ProjectDTO;
import com.example.EmployeeWeb.Project.model.Project;

import java.util.*;

import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectDTOMapper implements BaseMapper<Project, ProjectDTO> {

    @Override
    public Project toEntity(ProjectDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }

        Project project = new Project();

        project.setProjectId( dto.getProjectId() );
        project.setProjectName( dto.getProjectName() );
        project.setProjectType( dto.getProjectType() );
        project.setProjectDepartment( dto.getProjectDepartment() );
        project.setVpnUserName( dto.getVpnUserName() );
        project.setVpnPassword( dto.getVpnPassword() );
        project.setMediaInformation( dto.getMediaInformation() );
        Set<Long> set = dto.getEmployeeIds();
        if ( set != null ) {
            project.setEmployeeIds( new LinkedHashSet<Long>( set ) );
        }
        return project;
    }

    @Override
    public ProjectDTO toDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectId( project.getProjectId() );
        projectDTO.setProjectName( project.getProjectName() );
        projectDTO.setProjectType( project.getProjectType() );
        projectDTO.setProjectDepartment( project.getProjectDepartment() );
        projectDTO.setMediaInformation( project.getMediaInformation() );
        Set<Long> set = project.getEmployeeIds();

        if ( set != null ) {
            projectDTO.setEmployeeIds( new LinkedHashSet<Long>( set ) );
        }

        projectDTO.setVpnUserName( project.getVpnUserName() );
        projectDTO.setVpnPassword( project.getVpnPassword() );

        return projectDTO;
    }

    @Override
    public List<ProjectDTO> toListDTO(List<Project> projects) {
        if ( Objects.isNull(projects) ) {
            return null;
        }

        List<ProjectDTO> list = new ArrayList<ProjectDTO>( projects.size() );
        for ( Project project : projects ) {
            list.add( toDTO( project ) );
        }

        return list;
    }

}


