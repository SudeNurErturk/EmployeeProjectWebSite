package com.example.EmployeeWeb.otherInfo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class OtherInformationDTO implements Serializable {

    private Long id;

    private String address;

    private String bank;

    private String iban;

    private String emergencyPersonName;

    private String emergencyPersonPhone;

}
