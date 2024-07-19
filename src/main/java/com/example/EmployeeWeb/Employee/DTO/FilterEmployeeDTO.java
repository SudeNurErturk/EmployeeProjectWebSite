package com.example.EmployeeWeb.Employee.DTO;

import com.example.EmployeeWeb.Employee.model.Enum;
import com.example.EmployeeWeb.Employee.model.Level;

import com.example.EmployeeWeb.OtherInfo.DTO.OtherInformationDTO;

import com.example.EmployeeWeb.PersonalInformation.DTO.PersonalInformationDTO;
import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterEmployeeDTO {


    @JsonProperty(value="employeeName")

    private String employeeName;


    @JsonProperty(value="employeeSurname")
    private String employeeSurname;



    @JsonProperty(value="employeeEmail")
    private String employeeEmail;


    @JsonProperty(value="employeePhone")

    private String employeePhone;


    @JsonProperty(value="level")

    private Level level;

    @JsonProperty(value="otherInformation")
    private OtherInformationDTO otherInformation;


    @JsonProperty(value="personalInformation")
    private PersonalInformationDTO personalInformation;


    @JsonProperty(value="birthdate")
    private Date birthdate;


    @JsonProperty(value="team")
    private Enum.Team team;


    @JsonProperty(value="startingDate")
    private Date startingDate;


    @JsonProperty(value="endingDate")
    private Date endingDate;


    @JsonProperty(value="workingPlace")
    private Enum.WorkingPlace workingPlace;


    @JsonProperty(value="contractType")
    private Enum.ContractType contractType;



}
