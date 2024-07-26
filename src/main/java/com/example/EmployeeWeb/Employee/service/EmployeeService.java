package com.example.EmployeeWeb.Employee.service;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.EmployeeDTORequest;
import com.example.EmployeeWeb.Employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;

import com.example.EmployeeWeb.Employee.validation.EmployeeValidation;
import com.example.EmployeeWeb.OtherInfo.repository.OtherInfoRepository;
import com.example.EmployeeWeb.PersonalInformation.repository.PersonalInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    public List<Employee> getEmployees(Specification<Employee> spec, Sort sort) {
        return employeeRepository.findAll(spec, sort);
    }


    public Optional<Employee> getEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }


    @Transactional
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee saveEmployee(Employee employee) throws Exception {
        EmployeeDTO employeeDTO = employeeDTOMapper.toDTO(employee);

        employeeValidation.validateEmployee(employeeDTO);

        if (employee.getOtherInformation() != null) {
            otherInformationRepository.save(employee.getOtherInformation());
        }
        if (employee.getPersonalInformation() != null) {
            personalInformationRepository.save(employee.getPersonalInformation());
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) throws Exception {

        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElseThrow(() -> new Exception("Employee not found"));


        EmployeeDTO employeeDTO = employeeDTOMapper.toDTO(employee);
        boolean isPhoneChanged = !existingEmployee.getEmployeePhone().equals(employeeDTO.getEmployeePhone());
        boolean isEmailChanged = !existingEmployee.getEmployeeEmail().equals(employeeDTO.getEmployeeEmail());

            if (isPhoneChanged || isEmailChanged) {
                throw new ValidationException("You cannot change the employee email or phone number.");
            }

        if (isEmailChanged) {
            throw new ValidationException("You cannot change the employee email.");
        }

        return employeeRepository.saveAndFlush(employee);
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


    public List<EmployeeDTORequest> convertToDTORequestList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTO = employeeDTOMapper.toListDTO(employees);
        return employeeDTOMapper.toListDTOReguest(employeeDTO);
    }
}