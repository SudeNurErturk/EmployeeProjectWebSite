package com.example.EmployeeWeb.employee.dto;

import com.example.EmployeeWeb.enums.Enum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTORequest {

    private String employeeName;

    private String employeeSurname;

    private String employeeEmail;

    private String employeePhone;

    private Enum.Level level;

}
