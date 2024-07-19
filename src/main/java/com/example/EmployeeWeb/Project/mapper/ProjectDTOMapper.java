package com.example.EmployeeWeb.Project.mapper;

import com.example.EmployeeWeb.Project.DTO.ProjectDTO;
import com.example.EmployeeWeb.Project.model.Project;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectDTOMapper implements BaseMapper<Project, ProjectDTO> {

    @Override
    public Project toEntity(ProjectDTO dto) {
        if ( dto == null ) {
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
    public ProjectDTO toDTO(Project entity) {
        if ( entity == null ) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setProjectId( entity.getProjectId() );
        projectDTO.setProjectName( entity.getProjectName() );
        projectDTO.setProjectType( entity.getProjectType() );
        projectDTO.setProjectDepartment( entity.getProjectDepartment() );
        projectDTO.setMediaInformation( entity.getMediaInformation() );
        Set<Long> set = entity.getEmployeeIds();
        if ( set != null ) {
            projectDTO.setEmployeeIds( new LinkedHashSet<Long>( set ) );
        }
        projectDTO.setVpnUserName( entity.getVpnUserName() );
        projectDTO.setVpnPassword( entity.getVpnPassword() );

        return projectDTO;
    }

    @Override
    public List<ProjectDTO> toListDTO(List<Project> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProjectDTO> list = new ArrayList<ProjectDTO>( entities.size() );
        for ( Project project : entities ) {
            list.add( toDTO( project ) );
        }

        return list;
    }

}


