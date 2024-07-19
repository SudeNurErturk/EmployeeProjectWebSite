package com.example.EmployeeWeb.Employee.service;

import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;
import com.example.EmployeeWeb.Employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;

import com.example.EmployeeWeb.OtherInfo.repository.OtherInfoRepository;
import com.example.EmployeeWeb.PersonalInformation.repository.PersonalInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class EmployeeService {

    //private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeRepository employeeRepository;
    private final OtherInfoRepository otherInformationRepository;
    private final PersonalInfoRepository personalInformationRepository;


    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    public List<Employee> getEmployees(Specification<Employee> spec) {
        return employeeRepository.findAll(spec);
    }


    public Optional<Employee> getEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

//    @Transactional
//    public List<EmployeeDTORequest> getEmployeeByIdAsDTO(Long id) {
//        return employeeRepository.findAll().stream()
//                .map(employeeMapper::toDTORequest)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee saveEmployee(Employee employee) throws Exception {
        //  Employee employee = employeeMapper.toEntity(employeeDTO);

        Optional<Employee> existingEmployee = employeeRepository.findByEmployeeEmail(employee.getEmployeeEmail());
        if (existingEmployee.isPresent() && !existingEmployee.get().getId().equals(employee.getId())) {
            throw new Exception("Email already exists");
        }  Optional<Employee> existingEmployeeByPhone = employeeRepository.findByEmployeePhone(employee.getEmployeePhone());
        if (existingEmployeeByPhone.isPresent() && !existingEmployeeByPhone.get().getId().equals(employee.getId())) {
            throw new Exception("phone already exists");
        }

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




}