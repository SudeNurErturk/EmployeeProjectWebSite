package com.example.EmployeeWeb.Employee.mapper;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.EmployeeDTORequest;
import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.OtherInfo.mapper.OtherInformationDTOMapper;
import com.example.EmployeeWeb.PersonalInformation.mapper.PersonalInformationDTOMapper;
import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeDTOMapper implements BaseMapper<Employee, EmployeeDTO> {



        private final OtherInformationDTOMapper otherInformationDTOMapper;
        private final PersonalInformationDTOMapper personalInformationDTOMapper;

    public EmployeeDTOMapper(OtherInformationDTOMapper otherInformationDTOMapper, PersonalInformationDTOMapper personalInformationDTOMapper) {
        this.otherInformationDTOMapper = otherInformationDTOMapper;
        this.personalInformationDTOMapper = personalInformationDTOMapper;
    }

    @Override
        public Employee toEntity(EmployeeDTO dto) {
            if ( dto == null ) {
                return null;
            }

            Employee employee = new Employee();

            employee.setId( dto.getId() );
            employee.setEmployeeName( dto.getEmployeeName() );
            employee.setEmployeeSurname( dto.getEmployeeSurname() );
            employee.setLevel( dto.getLevel() );
            employee.setEmployeePhone( dto.getEmployeePhone() );
            employee.setEmployeeEmail( dto.getEmployeeEmail() );
            employee.setBirthdate( dto.getBirthdate() );
            employee.setWorkingPlace( dto.getWorkingPlace() );
            employee.setTeam( dto.getTeam() );
            employee.setStartingDate( dto.getStartingDate() );
            employee.setEndingDate( dto.getEndingDate() );
            employee.setContractType( dto.getContractType() );
            employee.setPersonalInformation( personalInformationDTOMapper.toEntity( dto.getPersonalInformation() ) );
            employee.setOtherInformation( otherInformationDTOMapper.toEntity( dto.getOtherInformation() ) );

            return employee;
        }

        @Override
        public EmployeeDTO toDTO(Employee entity) {
            if ( entity == null ) {
                return null;
            }

            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setId( entity.getId() );
            employeeDTO.setEmployeeName( entity.getEmployeeName() );
            employeeDTO.setEmployeeSurname( entity.getEmployeeSurname() );
            employeeDTO.setEmployeeEmail( entity.getEmployeeEmail() );
            employeeDTO.setEmployeePhone( entity.getEmployeePhone() );
            employeeDTO.setLevel( entity.getLevel() );
            employeeDTO.setOtherInformation( otherInformationDTOMapper.toDTO( entity.getOtherInformation() ) );
            employeeDTO.setPersonalInformation( personalInformationDTOMapper.toDTO( entity.getPersonalInformation() ) );
            employeeDTO.setBirthdate( entity.getBirthdate() );
            employeeDTO.setTeam( entity.getTeam() );
            employeeDTO.setStartingDate( entity.getStartingDate() );
            employeeDTO.setEndingDate( entity.getEndingDate() );
            employeeDTO.setWorkingPlace( entity.getWorkingPlace() );
            employeeDTO.setContractType( entity.getContractType() );

            return employeeDTO;
        }



   public EmployeeDTORequest toDTORequest(EmployeeDTO employee){
       if ( employee == null ) {
           return null;
       }

       EmployeeDTORequest employeeDTORequest = new EmployeeDTORequest();

       employeeDTORequest.setEmployeeName( employee.getEmployeeName() );
       employeeDTORequest.setEmployeeSurname( employee.getEmployeeSurname() );
       employeeDTORequest.setEmployeeEmail( employee.getEmployeeEmail() );
       employeeDTORequest.setEmployeePhone( employee.getEmployeePhone() );
       employeeDTORequest.setLevel( employee.getLevel() );


       return employeeDTORequest;
    }

        @Override
        public List<EmployeeDTO> toListDTO(List<Employee> entities) {
            if ( entities == null ) {
                return null;
            }

            List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( entities.size() );
            for ( Employee employee : entities ) {
                list.add( toDTO( employee ) );
            }

            return list;
        }




    public List<EmployeeDTORequest> toListDTOReguest(List<EmployeeDTO> employees){
         if ( employees == null ) {
             return null;
         }

         List<EmployeeDTORequest> list = new ArrayList<EmployeeDTORequest>( employees.size() );
         for ( EmployeeDTO employee : employees ) {
             list.add( toDTORequest( employee ) );
         }

         return list;
     }



    public List<FilterEmployeeDTO> dtoToFilter(List<EmployeeDTO> employeeDTOs) {
        if (employeeDTOs == null) {
            return null;
        }

        List<FilterEmployeeDTO> filterEmployeeDTOs = new ArrayList<>(employeeDTOs.size());
        for (EmployeeDTO dto : employeeDTOs) {
            filterEmployeeDTOs.add(toFilterEmployeeDTO(dto));
        }

        return filterEmployeeDTOs;
    }

    public FilterEmployeeDTO toFilterEmployeeDTO(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        FilterEmployeeDTO filterEmployeeDTO = new FilterEmployeeDTO();
        filterEmployeeDTO.setEmployeeName(dto.getEmployeeName());
        filterEmployeeDTO.setEmployeeSurname(dto.getEmployeeSurname());
        filterEmployeeDTO.setEmployeePhone(dto.getEmployeePhone());
        filterEmployeeDTO.setEmployeeEmail(dto.getEmployeeEmail());
        filterEmployeeDTO.setLevel(dto.getLevel());
        filterEmployeeDTO.setBirthdate(dto.getBirthdate());
        filterEmployeeDTO.setTeam(dto.getTeam());
        filterEmployeeDTO.setStartingDate(dto.getStartingDate());
        filterEmployeeDTO.setEndingDate(dto.getEndingDate());
        filterEmployeeDTO.setWorkingPlace(dto.getWorkingPlace());
        filterEmployeeDTO.setContractType(dto.getContractType());
//        filterEmployeeDTO.setOtherInformation( otherInformationDTOMapper.toDTO(dto.getOtherInformation()) );
//        filterEmployeeDTO.setPersonalInformation( personalInformationDTOMapper.toDTO( dto.getPersonalInformation()) );
//



        return filterEmployeeDTO;
    }


}










