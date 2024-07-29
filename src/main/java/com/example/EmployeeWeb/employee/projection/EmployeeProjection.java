package com.example.EmployeeWeb.employee.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;


public interface EmployeeProjection {

    @JsonProperty("FULL NAME")
    @Value("#{target.employeeName + ' ' + target.employeeSurname}")
    String getFullName();

    @JsonProperty("ID")
    String getId();

    @JsonProperty("EMAIL")
    String getEmployeeEmail();

    @JsonProperty("PHONE")
    String getEmployeePhone();

    @JsonProperty("LEVEL")
    String getLevel();

    @JsonProperty("CONTRACT TYPE")
    String getContractType();

    @JsonProperty("BIRTHDATE")
    String getBirthdate();

    @JsonProperty("TEAM")
    String getTeam();

    @JsonProperty("WORKING PLACE")
    String getWorkingPlace();

    @JsonProperty("STARTING DATE")
    String getStartingDate();

    @JsonProperty("ENDING DATE")
    String getEndingDate();

    @JsonProperty("PERSONAL INFORMATION")
    PersonalInfoProjection getPersonalInformation();

    @JsonProperty("OTHER INFORMATION")
    OtherInfoProjection getOtherInformation();

}
