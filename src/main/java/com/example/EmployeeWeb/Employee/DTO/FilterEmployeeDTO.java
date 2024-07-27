package com.example.EmployeeWeb.Employee.DTO;

import com.example.EmployeeWeb.enums.Enum;
import com.example.EmployeeWeb.Employee.model.Level;

import com.example.EmployeeWeb.OtherInfo.DTO.OtherInformationDTO;

import com.example.EmployeeWeb.PersonalInformation.DTO.PersonalInformationDTO;
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


    private String employeeName;



    private String employeeSurname;



    @JsonProperty(value="email")
    private String employeeEmail;


    @JsonProperty(value="phone")

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

    @Override
    public String toString() {
        return "FilterEmployeeDTO{" +
                "birthdate=" + birthdate +
                ", employeeName='" + employeeName + '\'' +
                ", employeeSurname='" + employeeSurname + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", level=" + level +
                ", otherInformation=" + otherInformation +
                ", personalInformation=" + personalInformation +
                ", team=" + team +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", workingPlace=" + workingPlace +
                ", contractType=" + contractType +
                '}';
    }
}
