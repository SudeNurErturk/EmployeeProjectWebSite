package com.example.EmployeeWeb.OtherInfo.mapper;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.OtherInfo.DTO.OtherInformationDTO;
import com.example.EmployeeWeb.OtherInfo.model.OtherInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.stereotype.Component;


@Component
public class OtherInformationDTOMapper implements BaseMapper<OtherInformation, OtherInformationDTO> {


    @Override
    public OtherInformation toEntity(OtherInformationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OtherInformation otherInformation = new OtherInformation();

        otherInformation.setId( dto.getId() );
        otherInformation.setAddress( dto.getAddress() );
        otherInformation.setBank( dto.getBank() );
        otherInformation.setIban( dto.getIban() );
        otherInformation.setEmergencyPersonName( dto.getEmergencyPersonName() );
        otherInformation.setEmergencyPersonPhone( dto.getEmergencyPersonPhone() );

        return otherInformation;
    }

    @Override
    public OtherInformationDTO toDTO(OtherInformation entity) {
        if ( entity == null ) {
            return null;
        }

        OtherInformationDTO otherInformationDTO = new OtherInformationDTO();

        otherInformationDTO.setId( entity.getId() );
        otherInformationDTO.setAddress( entity.getAddress() );
        otherInformationDTO.setBank( entity.getBank() );
        otherInformationDTO.setIban( entity.getIban() );
        otherInformationDTO.setEmergencyPersonName( entity.getEmergencyPersonName() );
        otherInformationDTO.setEmergencyPersonPhone( entity.getEmergencyPersonPhone() );

        return otherInformationDTO;
    }

    @Override
    public List<OtherInformationDTO> toListDTO(List<OtherInformation> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OtherInformationDTO> list = new ArrayList<OtherInformationDTO>( entities.size() );
        for ( OtherInformation otherInformation : entities ) {
            list.add( toDTO( otherInformation ) );
        }

        return list;
    }



}
