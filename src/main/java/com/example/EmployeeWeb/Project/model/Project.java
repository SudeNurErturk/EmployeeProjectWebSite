package com.example.EmployeeWeb.Project.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "PROJECTS")
public class Project {


    @Serial
    private static final long serialVersionUID = 11273137873139349L;


    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @SequenceGenerator(name = "GEN_PROJECT", sequenceName = "SEQ_PROJECT", allocationSize = 1)
    @GeneratedValue(generator = "GEN_PROJECT", strategy = GenerationType.SEQUENCE )
    private Long projectId;


    @Column(name= "Project_Name",unique = true)
    private String projectName;


    @Enumerated(EnumType.STRING)
    @Column(name = "Project_Type")
    private ProjectType projectType;


    @Column(name= "Department")
    private String projectDepartment;


    @Column(name = "VPN_User_Name")
    private String vpnUserName;


    @Column(name = "VPN_password")
    private String vpnPassword;


    @Column(name ="media_Information")
    private String mediaInformation;


    @ElementCollection
    @CollectionTable(name = "project_employees", joinColumns = @JoinColumn(name = "project_Id"))
    @Column(name = "employee_Id")
    private Set<Long> employeeIds; // Sadece Employee ID'leri tutan set


    public enum ProjectType {
        PROJECT, SUPPORT, INTERNAL, HR, SALES, PRODUCT
    }


    // VPN password encryption example (this is a simplistic example, not for production use)
    @Transient
    public String getEncryptedVpnPassword() {
        return "encrypted:" + this.vpnPassword;
    }

}
