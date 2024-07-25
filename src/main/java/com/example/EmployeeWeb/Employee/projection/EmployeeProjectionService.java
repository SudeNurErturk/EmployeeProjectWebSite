package com.example.EmployeeWeb.Employee.projection;

import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
