package com.example.EmployeeWeb.employee.projection;

import com.example.EmployeeWeb.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProjectionService {

    private  final EmployeeRepository employeeRepository ;

    public EmployeeProjectionService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeProjection> getEmployeeFullNames() {
        return employeeRepository.findEmployeeFullNames();
    }
    public List<EmployeeProjection> getAllEmployeeProjections() {
        return employeeRepository.findAllEmployees();
    }
}
