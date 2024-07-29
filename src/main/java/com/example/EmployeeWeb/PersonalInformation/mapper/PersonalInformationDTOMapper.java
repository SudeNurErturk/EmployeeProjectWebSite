package com.example.EmployeeWeb.PersonalInformation.mapper;

import com.example.EmployeeWeb.PersonalInformation.dto.PersonalInformationDTO;
import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.stereotype.Component;


@Component
public class PersonalInformationDTOMapper implements BaseMapper<PersonalInformation, PersonalInformationDTO> {

    @Override
    public PersonalInformation toEntity(PersonalInformationDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }

        PersonalInformation personalInformation = new PersonalInformation();

        personalInformation.setId( dto.getId() );
        personalInformation.setBirthdate( dto.getBirthdate() );
        personalInformation.setPersonalSocialSecurityNumber( dto.getPersonalSocialSecurityNumber() );
        personalInformation.setMilitaryService(dto.getMilitaryService());
        personalInformation.setGender( dto.getGender() );
        personalInformation.setMaritalStatus(dto.getMaritalStatus());
       // personalInformation.setEmployee( dto.getEmployee() );

        return personalInformation;
    }

    @Override
    public PersonalInformationDTO toDTO(PersonalInformation personalInformation) {
        if ( Objects.isNull(personalInformation)  ) {
            return null;
        }

        PersonalInformationDTO personalInformationDTO = new PersonalInformationDTO();

        personalInformationDTO.setId( personalInformation.getId() );
        personalInformationDTO.setBirthdate( personalInformation.getBirthdate() );
        personalInformationDTO.setPersonalSocialSecurityNumber( personalInformation.getPersonalSocialSecurityNumber() );
        personalInformationDTO.setMilitaryService(personalInformation.getMilitaryService());
        personalInformationDTO.setGender( personalInformation.getGender() );
        personalInformationDTO.setMaritalStatus(personalInformation.getMaritalStatus());


        return personalInformationDTO;
    }

    @Override
    public List<PersonalInformationDTO> toListDTO(List<PersonalInformation> personalInformations) {
        if ( Objects.isNull(personalInformations)  ) {
            return null;
        }

        List<PersonalInformationDTO> list = new ArrayList<PersonalInformationDTO>( personalInformations.size() );
        for ( PersonalInformation personalInformation : personalInformations ) {
            list.add( toDTO( personalInformation ) );
        }

        return list;

    }
}
