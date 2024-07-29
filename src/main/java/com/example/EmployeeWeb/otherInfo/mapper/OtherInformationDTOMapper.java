package com.example.EmployeeWeb.otherInfo.mapper;

import com.example.EmployeeWeb.otherInfo.dto.OtherInformationDTO;
import com.example.EmployeeWeb.otherInfo.model.OtherInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.stereotype.Component;


@Component
public class OtherInformationDTOMapper implements BaseMapper<OtherInformation, OtherInformationDTO> {


    @Override
    public OtherInformation toEntity(OtherInformationDTO dto) {
        if ( Objects.isNull(dto) ) {
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
    public OtherInformationDTO toDTO(OtherInformation otherInformation) {
        if (Objects.isNull(otherInformation) ) {
            return null;
        }

        OtherInformationDTO otherInformationDTO = new OtherInformationDTO();

        otherInformationDTO.setId( otherInformation.getId() );
        otherInformationDTO.setAddress( otherInformation.getAddress() );
        otherInformationDTO.setBank( otherInformation.getBank() );
        otherInformationDTO.setIban( otherInformation.getIban() );
        otherInformationDTO.setEmergencyPersonName( otherInformation.getEmergencyPersonName() );
        otherInformationDTO.setEmergencyPersonPhone( otherInformation.getEmergencyPersonPhone() );

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
