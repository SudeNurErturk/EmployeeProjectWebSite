package com.example.EmployeeWeb.Employee.DTO;

import com.example.EmployeeWeb.Employee.model.Enum;
import com.example.EmployeeWeb.Employee.model.Level;
import com.example.EmployeeWeb.OtherInfo.DTO.OtherInformationDTO;
import com.example.EmployeeWeb.PersonalInformation.DTO.PersonalInformationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeDTO implements Serializable {


    private Long id;

    @NotEmpty
    private String employeeName;

    @NotEmpty
    private String employeeSurname;

    @NotEmpty
    private String employeeEmail;

    @NotEmpty
    private String employeePhone;

    @NotNull
    private Level level;

    private OtherInformationDTO otherInformation;

    private PersonalInformationDTO personalInformation;

    private Date birthdate;

    private Enum.Team team;

    private Date startingDate;

    private Date endingDate;

    @NotNull
    private Enum.WorkingPlace workingPlace;

    private Enum.ContractType contractType;

}