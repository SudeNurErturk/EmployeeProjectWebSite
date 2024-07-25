package com.example.EmployeeWeb.Employee.projection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


public interface EmployeeProjection {
    @Value("#{target.employeeName + ' ' + target.employeeSurname}")
    String getFullName();
    String getId();
    String getEmployeeEmail();
    String getEmployeePhone();
    String getLevel();
    String getContractType();
    PersonalInfoProjection getPersonalInformation();
    OtherInfoProjection getOtherInformation();
    String getBirthdate();
    String getTeam();
    String getWorkingPlace();
    String getStartingDate();
    String getEndingDate();




}
