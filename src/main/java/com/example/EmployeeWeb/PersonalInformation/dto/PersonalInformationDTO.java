package com.example.EmployeeWeb.PersonalInformation.dto;

import com.example.EmployeeWeb.enums.Enum;
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

    private Enum.MilitaryService militaryService;

    private String gender;

    private Enum.MaritalStatus maritalStatus;



}
