package com.example.EmployeeWeb.PersonalInformation.mapper;

import com.example.EmployeeWeb.PersonalInformation.DTO.PersonalInformationDTO;
import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import java.util.ArrayList;
import java.util.List;

import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.stereotype.Component;


@Component
public class PersonalInformationDTOMapper implements BaseMapper<PersonalInformation, PersonalInformationDTO> {

    @Override
    public PersonalInformation toEntity(PersonalInformationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PersonalInformation personalInformation = new PersonalInformation();

        personalInformation.setId( dto.getId() );
        personalInformation.setBirthdate( dto.getBirthdate() );
        personalInformation.setPersonalSocialSecurityNumber( dto.getPersonalSocialSecurityNumber() );
        personalInformation.setMilitaryService( dto.getMilitaryService() );
        personalInformation.setGender( dto.getGender() );
        personalInformation.setMaritalStatus( dto.getMaritalStatus() );
        personalInformation.setEmployee( dto.getEmployee() );

        return personalInformation;
    }

    @Override
    public PersonalInformationDTO toDTO(PersonalInformation entity) {
        if ( entity == null ) {
            return null;
        }

        PersonalInformationDTO personalInformationDTO = new PersonalInformationDTO();

        personalInformationDTO.setId( entity.getId() );
        personalInformationDTO.setBirthdate( entity.getBirthdate() );
        personalInformationDTO.setPersonalSocialSecurityNumber( entity.getPersonalSocialSecurityNumber() );
        personalInformationDTO.setMilitaryService( entity.getMilitaryService() );
        personalInformationDTO.setGender( entity.getGender() );
        personalInformationDTO.setMaritalStatus( entity.getMaritalStatus() );
        personalInformationDTO.setEmployee( entity.getEmployee() );

        return personalInformationDTO;
    }

    @Override
    public List<PersonalInformationDTO> toListDTO(List<PersonalInformation> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PersonalInformationDTO> list = new ArrayList<PersonalInformationDTO>( entities.size() );
        for ( PersonalInformation personalInformation : entities ) {
            list.add( toDTO( personalInformation ) );
        }

        return list;

    }
}
