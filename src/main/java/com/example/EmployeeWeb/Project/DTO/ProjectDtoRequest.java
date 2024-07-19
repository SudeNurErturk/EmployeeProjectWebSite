package com.example.EmployeeWeb.Project.DTO;

import com.example.EmployeeWeb.Project.model.Project;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDtoRequest {

    private Long projectId;

    private String projectName;

    private Project projectType;

    private String projectDepartment;

    private String mediaInformation;

    private Set<Long> employeeIds;



    //GETTER AND SETTER


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Set<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(Set<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public String getMediaInformation() {
        return mediaInformation;
    }

    public void setMediaInformation(String mediaInformation) {
        this.mediaInformation = mediaInformation;
    }

    public String getProjectDepartment() {
        return projectDepartment;
    }

    public void setProjectDepartment(String projectDepartment) {
        this.projectDepartment = projectDepartment;
    }





    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Project getProjectType() {
        return projectType;
    }

    public void setProjectType(Project projectType) {
        this.projectType = projectType;
    }
}
