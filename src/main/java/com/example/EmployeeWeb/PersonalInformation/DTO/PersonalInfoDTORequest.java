package com.example.EmployeeWeb.PersonalInformation.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfoDTORequest {


    private Long id;





    private Date birthDate;


      //GETTER SETTER
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }



}
