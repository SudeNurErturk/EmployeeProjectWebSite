package com.example.EmployeeWeb.Employee.service;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.EmployeeDTORequest;
import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;
import com.example.EmployeeWeb.Employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.enums.Enum;
import com.example.EmployeeWeb.Employee.model.Level;

import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;


import com.example.EmployeeWeb.Employee.specification.EmployeeSpecification;
import com.example.EmployeeWeb.Employee.validation.EmployeeValidation;
import com.example.EmployeeWeb.OtherInfo.DTO.OtherInformationDTO;
import com.example.EmployeeWeb.OtherInfo.mapper.OtherInformationDTOMapper;
import com.example.EmployeeWeb.OtherInfo.model.OtherInformation;
import com.example.EmployeeWeb.OtherInfo.repository.OtherInfoRepository;
import com.example.EmployeeWeb.PersonalInformation.DTO.PersonalInformationDTO;
import com.example.EmployeeWeb.PersonalInformation.mapper.PersonalInformationDTOMapper;
import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import com.example.EmployeeWeb.PersonalInformation.repository.PersonalInfoRepository;
import com.example.EmployeeWeb.exception.GlobalExceptionHandler;
import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OtherInfoRepository otherInformationRepository;
    private final PersonalInfoRepository personalInformationRepository;
    private final EmployeeValidation employeeValidation;
    private final EmployeeDTOMapper employeeDTOMapper;


    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }



    @Transactional
    public Employee saveEmployee(Employee employee) throws EntityNotFoundException {
        EmployeeDTO employeeDTO = employeeDTOMapper.toDTO(employee);
        employeeValidation.validateEmployee(employeeDTO);
        if (!employeeValidation.isEmailExists(employee.getEmployeeEmail()) && !employeeValidation.isPhoneExists(employee.getEmployeePhone()) && employee.getOtherInformation() != null) {
            otherInformationRepository.save(employee.getOtherInformation());
        }
        if (!employeeValidation.isEmailExists(employee.getEmployeeEmail()) && !employeeValidation.isPhoneExists(employee.getEmployeePhone()) && employee.getPersonalInformation() != null) {
            personalInformationRepository.save(employee.getPersonalInformation());
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public EmployeeDTO updateEmployee(Long employeeId ,EmployeeDTO employeeDTO) throws Exception {
        Employee existingEmployee = employeeRepository.findForEmployeeId(employeeId);

            boolean isPhoneChanged = !existingEmployee.getEmployeePhone().equals(employeeDTO.getEmployeePhone());
            boolean isEmailChanged = !existingEmployee.getEmployeeEmail().equals(employeeDTO.getEmployeeEmail());

            if (isPhoneChanged || isEmailChanged) {
                throw new ValidationException("You cannot change the employee email or phone number.");
            }
        existingEmployee.setEmployeeName(employeeDTO.getEmployeeName());
        existingEmployee.setEmployeeSurname(employeeDTO.getEmployeeSurname());
        existingEmployee.setLevel(Level.valueOf(String.valueOf(employeeDTO.getLevel())));
        existingEmployee.setEmployeePhone(employeeDTO.getEmployeePhone());
        existingEmployee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
        existingEmployee.setBirthdate(employeeDTO.getBirthdate());
        existingEmployee.setWorkingPlace(Enum.WorkingPlace.valueOf(String.valueOf(employeeDTO.getWorkingPlace())));
        existingEmployee.setContractType(Enum.ContractType.valueOf(String.valueOf(employeeDTO.getContractType())));
        existingEmployee.setTeam(Enum.Team.valueOf(String.valueOf(employeeDTO.getTeam())));
        existingEmployee.setStartingDate(employeeDTO.getStartingDate());
        existingEmployee.setEndingDate(employeeDTO.getEndingDate());


        if (existingEmployee.getPersonalInformation() == null) {
            existingEmployee.setPersonalInformation(new PersonalInformation());
        }
        PersonalInformationDTO personalInfoDTO = employeeDTO.getPersonalInformation();
        existingEmployee.getPersonalInformation().setBirthdate(personalInfoDTO.getBirthdate());
        existingEmployee.getPersonalInformation().setPersonalSocialSecurityNumber(personalInfoDTO.getPersonalSocialSecurityNumber());
        existingEmployee.getPersonalInformation().setMilitaryService(personalInfoDTO.getMilitaryService());
        existingEmployee.getPersonalInformation().setGender((personalInfoDTO.getGender()));
        existingEmployee.getPersonalInformation().setMaritalStatus(personalInfoDTO.getMaritalStatus());


        if (existingEmployee.getOtherInformation() == null) {
            existingEmployee.setOtherInformation(new OtherInformation());
        }
        OtherInformationDTO otherInfoDTO = employeeDTO.getOtherInformation();
        existingEmployee.getOtherInformation().setAddress(otherInfoDTO.getAddress());
        existingEmployee.getOtherInformation().setBank(otherInfoDTO.getBank());
        existingEmployee.getOtherInformation().setIban(otherInfoDTO.getIban());
        existingEmployee.getOtherInformation().setEmergencyPersonName(otherInfoDTO.getEmergencyPersonName());
        existingEmployee.getOtherInformation().setEmergencyPersonPhone(otherInfoDTO.getEmergencyPersonPhone());
        Employee savedEmployee = employeeRepository.save(existingEmployee);
            return employeeDTOMapper.toDTO(savedEmployee);
    }




    @Transactional
    public void deleteEmployeeById(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.getProjects().clear();
            employeeRepository.delete(employee);
            System.out.println("Employee deleted: " + employee.getEmployeeEmail());
        } else {
            throw new EntityNotFoundException("Employee with ID " + employeeId + " not found");
        }
    }
}