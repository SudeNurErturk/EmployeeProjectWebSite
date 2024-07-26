package com.example.EmployeeWeb.Employee.service;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.EmployeeDTORequest;
import com.example.EmployeeWeb.Employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;

import com.example.EmployeeWeb.Employee.validation.EmployeeValidation;
import com.example.EmployeeWeb.OtherInfo.mapper.OtherInformationDTOMapper;
import com.example.EmployeeWeb.OtherInfo.repository.OtherInfoRepository;
import com.example.EmployeeWeb.PersonalInformation.mapper.PersonalInformationDTOMapper;
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


@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OtherInfoRepository otherInformationRepository;
    private final PersonalInfoRepository personalInformationRepository;
    private final EmployeeValidation employeeValidation;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final OtherInformationDTOMapper otherInformationDTOMapper;
    private final PersonalInformationDTOMapper personalInformationDTOMapper;
    private final GlobalExceptionHandler globalExceptionHandler;


    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployees(Specification<Employee> spec, Sort sort) {
        return employeeRepository.findAll(spec, sort);
    }


    public Employee getEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findForEmployeeId(employeeId);
    }



    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
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

    @Transactional(readOnly = true)
    public Employee updateEmployee(Employee employee) throws Exception {

        Employee existingEmployee = employeeRepository.findForEmployeeId(employee.getId());
        if (existingEmployee != null) {
            EmployeeDTO employeeDTO = employeeDTOMapper.toDTO(employee);
            boolean isPhoneChanged = !existingEmployee.getEmployeePhone().equals(employeeDTO.getEmployeePhone());
            boolean isEmailChanged = !existingEmployee.getEmployeeEmail().equals(employeeDTO.getEmployeeEmail());

            if (isPhoneChanged || isEmailChanged) {
                throw new ValidationException("You cannot change the employee email or phone number.");
            }

            if (isEmailChanged) {
                throw new ValidationException("You cannot change the employee email.");
            }


        }
        return employeeRepository.save(employee);
    }


    @Transactional
    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);
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

   @Transactional
    public List<EmployeeDTORequest> convertToDTORequestList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTO = employeeDTOMapper.toListDTO(employees);
        return employeeDTOMapper.toListDTOReguest(employeeDTO);
    }
}