package com.example.EmployeeWeb.PersonalInformation.model;

import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.enums.Enum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class PersonalInformation {


    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @SequenceGenerator(name = "GEN_PERSONALINFO", sequenceName = "SEQ_PERSONALINFO", allocationSize = 1)
    @GeneratedValue(generator = "GEN_PERSONALINFO", strategy = GenerationType.SEQUENCE )
    private Long id;


    private Date birthdate;


    @NotEmpty
    private String personalSocialSecurityNumber;

    @Enumerated(EnumType.STRING)
    private Enum.MilitaryService militaryService;


    @Column(name = "Gender")
    private String gender;


    @Enumerated(EnumType.STRING)
    private Enum.MaritalStatus maritalStatus;


}
