package com.example.EmployeeWeb.PersonalInformation.DTO;

import com.example.EmployeeWeb.Employee.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class PersonalInformationDTO implements Serializable {

    private Long id;

    private Date birthdate;

    private String personalSocialSecurityNumber;

    private String militaryService;

    private String gender;

    private String maritalStatus;

    private Employee employee;

}
