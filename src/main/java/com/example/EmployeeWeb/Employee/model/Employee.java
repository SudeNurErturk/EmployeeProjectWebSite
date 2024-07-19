package com.example.EmployeeWeb.Employee.model;


import com.example.EmployeeWeb.OtherInfo.model.OtherInformation;
import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import com.example.EmployeeWeb.Project.model.Project;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.io.Serial;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class Employee  {

    @Serial
    private static final long serialVersionUID = 11273137873139349L;

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @SequenceGenerator(name = "GEN_EMPLOYEE", sequenceName = "SEQ_EMPLOYEE", allocationSize = 1)
    @GeneratedValue(generator = "GEN_EMPLOYEE", strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "NAME")
    private String employeeName;

    @Column(name = "SURNAME")
    private String employeeSurname;

    @Enumerated(EnumType.STRING)
    @Column(name = "LEVEL")
    private Level level;

    @Column(name = "PHONE_NUMBER")
    private String employeePhone;

    @Email
    @Column(name = "EMAIL")
    private String employeeEmail;

    @Column(name = "BIRTH_DATE")
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "WORKING_PLACE")
    private Enum.WorkingPlace workingPlace;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEAM")
    private Enum.Team team;

    @Column(name = "STARTING DATE")
    private Date startingDate;

    @Column(name = "ENDING DATE")
    private Date endingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTRACT_TYPE")
    private Enum.ContractType contractType;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSONAL_INFORMATION_ID")
    private PersonalInformation personalInformation;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "OTHER_INFORMATION_ID")
    private OtherInformation otherInformation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )

    private Set<Project> projects;

}

