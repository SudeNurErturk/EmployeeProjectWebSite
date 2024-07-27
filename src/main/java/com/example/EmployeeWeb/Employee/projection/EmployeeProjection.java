package com.example.EmployeeWeb.Employee.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


public interface EmployeeProjection {
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
    @JsonProperty("PERSONAL INFORMATION")
    PersonalInfoProjection getPersonalInformation();
    @JsonProperty("OTHER INFORMATION")
    OtherInfoProjection getOtherInformation();
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




}
