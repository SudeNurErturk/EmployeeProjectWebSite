package com.example.EmployeeWeb.employee.service;

import com.example.EmployeeWeb.employee.dto.EmployeeDTO;
import com.example.EmployeeWeb.employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.employee.entity.Employee;
import com.example.EmployeeWeb.enums.Enum;

import com.example.EmployeeWeb.employee.repository.EmployeeRepository;


import com.example.EmployeeWeb.employee.validation.EmployeeValidation;
import com.example.EmployeeWeb.otherInfo.dto.OtherInformationDTO;
import com.example.EmployeeWeb.otherInfo.model.OtherInformation;
import com.example.EmployeeWeb.otherInfo.repository.OtherInfoRepository;
import com.example.EmployeeWeb.PersonalInformation.dto.PersonalInformationDTO;
import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import com.example.EmployeeWeb.PersonalInformation.repository.PersonalInfoRepository;
import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@Service
public class EmployeeService {


    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeValidation employeeValidation;
    private final EmployeeRepository employeeRepository;
    private final OtherInfoRepository otherInformationRepository;
    private final PersonalInfoRepository personalInformationRepository;


    @Transactional(readOnly = true)
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
    public EmployeeDTO updateEmployee(Long employeeId ,EmployeeDTO employeeDTO) {

        Employee existingEmployee = employeeRepository.findForEmployeeId(employeeId);

        boolean isPhoneChanged = !existingEmployee.getEmployeePhone().equals(employeeDTO.getEmployeePhone());
        boolean isEmailChanged = !existingEmployee.getEmployeeEmail().equals(employeeDTO.getEmployeeEmail());

        if (isPhoneChanged || isEmailChanged) {
                throw new ValidationException("You cannot change the employee email or phone number.");
        }

        existingEmployee.setEmployeeName(employeeDTO.getEmployeeName());
        existingEmployee.setEmployeeSurname(employeeDTO.getEmployeeSurname());
        existingEmployee.setLevel(Enum.Level.valueOf(String.valueOf(employeeDTO.getLevel())));
        existingEmployee.setEmployeePhone(employeeDTO.getEmployeePhone());
        existingEmployee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
        existingEmployee.setBirthdate(employeeDTO.getBirthdate());
        existingEmployee.setWorkingPlace(Enum.WorkingPlace.valueOf(String.valueOf(employeeDTO.getWorkingPlace())));
        existingEmployee.setContractType(Enum.ContractType.valueOf(String.valueOf(employeeDTO.getContractType())));
        existingEmployee.setTeam(Enum.Team.valueOf(String.valueOf(employeeDTO.getTeam())));
        existingEmployee.setStartingDate(employeeDTO.getStartingDate());
        existingEmployee.setEndingDate(employeeDTO.getEndingDate());

        if (Objects.isNull(existingEmployee.getPersonalInformation())) {
            existingEmployee.setPersonalInformation(new PersonalInformation());
        }

        PersonalInformationDTO personalInfoDTO = employeeDTO.getPersonalInformation();
        existingEmployee.getPersonalInformation().setBirthdate(personalInfoDTO.getBirthdate());
        existingEmployee.getPersonalInformation().setPersonalSocialSecurityNumber(personalInfoDTO.getPersonalSocialSecurityNumber());
        existingEmployee.getPersonalInformation().setMilitaryService(personalInfoDTO.getMilitaryService());
        existingEmployee.getPersonalInformation().setGender((personalInfoDTO.getGender()));
        existingEmployee.getPersonalInformation().setMaritalStatus(personalInfoDTO.getMaritalStatus());

        if (Objects.isNull(existingEmployee.getOtherInformation())) {
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

        Employee employee = employeeRepository.findForEmployeeId(employeeId);

        if (Objects.nonNull(employee)) {
            employee.getProjects().clear();
            employeeRepository.delete(employee);
            System.out.println("Employee deleted: " + employee.getEmployeeEmail());
        } else {
            throw new EntityNotFoundException("Employee with ID " + employeeId + " not found");
        }
    }
}