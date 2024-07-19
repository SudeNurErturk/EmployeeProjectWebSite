package com.example.EmployeeWeb.Employee.DTO;

import com.example.EmployeeWeb.Employee.model.Level;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTORequest {



    private String employeeName;

    private String employeeSurname;

    private String employeeEmail;

    private String employeePhone;

    private Level level;



    //Getter and Setter

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }






    public void setLevel(Level level) {
        this.level = level;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = Level.valueOf(level);
    }
}
