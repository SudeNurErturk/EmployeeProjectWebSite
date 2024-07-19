package com.example.EmployeeWeb.Project.DTO;


import com.example.EmployeeWeb.Project.model.Project;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class ProjectDTO implements Serializable {

    private Long projectId;

    private String projectName;

    private Project.ProjectType projectType;

    private String projectDepartment;

    private String mediaInformation;

    private Set<Long> employeeIds;

    private String vpnUserName;

    private String vpnPassword;

}