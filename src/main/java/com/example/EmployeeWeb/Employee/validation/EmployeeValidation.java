package com.example.EmployeeWeb.Employee.validation;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;


@Component
public class EmployeeValidation {

    private final EmployeeRepository employeeRepository;


    public EmployeeValidation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee validateEmployee(EmployeeDTO employeeDTO) throws ValidationException {
        if (isPhoneExists(employeeDTO.getEmployeePhone())) {
            throw new ValidationException("Phone number already exists.");
        }

      else   if (isEmailExists(employeeDTO.getEmployeeEmail())) {
            throw new ValidationException("Email address already exists.");
        }
        return null;
    }

    public boolean isPhoneExists(String EmployeePhone) {
        return employeeRepository.findByEmployeePhone(EmployeePhone);
    }

    public boolean isEmailExists(String email) {
        return employeeRepository.findByEmployeeEmail(email);
    }


}
