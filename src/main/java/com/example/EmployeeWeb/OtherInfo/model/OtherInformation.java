package com.example.EmployeeWeb.OtherInfo.model;

import com.example.EmployeeWeb.Employee.model.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "OTHER_INFORMATIONS")
@AllArgsConstructor
@NoArgsConstructor
public class OtherInformation {

    @Serial
    private static final long serialVersionUID = 11273137873139349L;


    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @SequenceGenerator(name = "GEN_OTHER_INFORMATION", sequenceName = "SEQ_OTHER_INFORMATION", allocationSize = 1)
    @GeneratedValue(generator = "GEN_OTHER_INFORMATION", strategy = GenerationType.SEQUENCE )
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BANK")
    private String bank;

    @Column(name = "IBAN")
    private String iban;

    @Column(name = "EMERGENY_PERSON_NAME")
    private String emergencyPersonName;

    @Column(name = "EMERGENCY_PERSON_PHONE")
    private String emergencyPersonPhone;


}
