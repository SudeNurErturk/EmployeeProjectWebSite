package com.example.EmployeeWeb.employee.mapper;

import com.example.EmployeeWeb.employee.dto.EmployeeDTO;
import com.example.EmployeeWeb.employee.dto.EmployeeDTORequest;
import com.example.EmployeeWeb.employee.entity.Employee;
import com.example.EmployeeWeb.otherInfo.mapper.OtherInformationDTOMapper;
import com.example.EmployeeWeb.PersonalInformation.mapper.PersonalInformationDTOMapper;
import com.example.EmployeeWeb.common.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EmployeeDTOMapper implements BaseMapper<Employee, EmployeeDTO> {

        private final OtherInformationDTOMapper otherInformationDTOMapper;
        private final PersonalInformationDTOMapper personalInformationDTOMapper;

    public EmployeeDTOMapper(OtherInformationDTOMapper otherInformationDTOMapper, PersonalInformationDTOMapper personalInformationDTOMapper) {
        this.otherInformationDTOMapper = otherInformationDTOMapper;
        this.personalInformationDTOMapper = personalInformationDTOMapper;
    }


    @Override
        public Employee toEntity(EmployeeDTO employeeDTO) {

            if (Objects.isNull(employeeDTO)) {
                return null;
            }

            Employee employee = new Employee();

            employee.setId( employeeDTO.getId() );
            employee.setEmployeeName( employeeDTO.getEmployeeName() );
            employee.setEmployeeSurname( employeeDTO.getEmployeeSurname() );
            employee.setLevel( employeeDTO.getLevel() );
            employee.setEmployeePhone( employeeDTO.getEmployeePhone() );
            employee.setEmployeeEmail( employeeDTO.getEmployeeEmail() );
            employee.setBirthdate( employeeDTO.getBirthdate() );
            employee.setWorkingPlace( employeeDTO.getWorkingPlace() );
            employee.setTeam( employeeDTO.getTeam() );
            employee.setStartingDate( employeeDTO.getStartingDate() );
            employee.setEndingDate( employeeDTO.getEndingDate() );
            employee.setContractType( employeeDTO.getContractType() );
            employee.setPersonalInformation( personalInformationDTOMapper.toEntity( employeeDTO.getPersonalInformation() ) );
            employee.setOtherInformation( otherInformationDTOMapper.toEntity( employeeDTO.getOtherInformation() ) );

            return employee;
        }

        @Override
        public EmployeeDTO toDTO(Employee employee) {

            if (Objects.isNull(employee)) {
                return null;
            }

            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setId( employee.getId() );
            employeeDTO.setEmployeeName( employee.getEmployeeName() );
            employeeDTO.setEmployeeSurname( employee.getEmployeeSurname() );
            employeeDTO.setEmployeeEmail( employee.getEmployeeEmail() );
            employeeDTO.setEmployeePhone( employee.getEmployeePhone() );
            employeeDTO.setLevel( employee.getLevel() );
            employeeDTO.setOtherInformation( otherInformationDTOMapper.toDTO( employee.getOtherInformation() ) );
            employeeDTO.setPersonalInformation( personalInformationDTOMapper.toDTO( employee.getPersonalInformation() ) );
            employeeDTO.setBirthdate( employee.getBirthdate() );
            employeeDTO.setTeam( employee.getTeam() );
            employeeDTO.setStartingDate( employee.getStartingDate() );
            employeeDTO.setEndingDate( employee.getEndingDate() );
            employeeDTO.setWorkingPlace( employee.getWorkingPlace() );
            employeeDTO.setContractType( employee.getContractType() );

            return employeeDTO;
        }

   public EmployeeDTORequest toDTORequest(EmployeeDTO employeeDTO){

       if (Objects.isNull(employeeDTO)) {
           return null;
       }

       EmployeeDTORequest employeeDTORequest = new EmployeeDTORequest();

       employeeDTORequest.setEmployeeName( employeeDTO.getEmployeeName() );
       employeeDTORequest.setEmployeeSurname( employeeDTO.getEmployeeSurname() );
       employeeDTORequest.setEmployeeEmail( employeeDTO.getEmployeeEmail() );
       employeeDTORequest.setEmployeePhone( employeeDTO.getEmployeePhone() );
       employeeDTORequest.setLevel( employeeDTO.getLevel() );


       return employeeDTORequest;
    }

        @Override
        public List<EmployeeDTO> toListDTO(List<Employee> employees) {

        if (Objects.isNull(employees)) {
            return null;
        }

            List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employees.size() );

            for ( Employee employee : employees ) {
                list.add( toDTO( employee ) );
            }

            return list;
        }

    public List<EmployeeDTORequest> toListDTOReguest(List<EmployeeDTO> employeeDTOS){

        if (Objects.isNull(employeeDTOS)) {
            return null;
        }

         List<EmployeeDTORequest> list = new ArrayList<EmployeeDTORequest>( employeeDTOS.size() );

         for ( EmployeeDTO employee : employeeDTOS ) {
             list.add( toDTORequest( employee ) );
         }

         return list;
     }

    public Page<EmployeeDTO> toPageDTO(Page<Employee> employeePage) {
        List<EmployeeDTO> employeeDTOS = employeePage.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(employeeDTOS, employeePage.getPageable(), employeePage.getTotalElements());
    }

}
