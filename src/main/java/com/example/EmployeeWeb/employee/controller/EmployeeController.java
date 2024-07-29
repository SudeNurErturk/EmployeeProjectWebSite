package com.example.EmployeeWeb.employee.controller;


import com.example.EmployeeWeb.employee.dto.EmployeeDTO;
import com.example.EmployeeWeb.employee.dto.EmployeeDTORequest;
import com.example.EmployeeWeb.employee.dto.FilterEmployeeDTO;

import com.example.EmployeeWeb.employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.employee.projection.EmployeeProjection;
import com.example.EmployeeWeb.employee.projection.EmployeeProjectionService;
import com.example.EmployeeWeb.employee.repository.EmployeeRepository;
import com.example.EmployeeWeb.employee.service.EmployeeService;
import com.example.EmployeeWeb.employee.entity.Employee;

import com.example.EmployeeWeb.employee.specification.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeRepository employeeRepository;
    private final EmployeeProjectionService employeeProjectionService;


    public EmployeeController(EmployeeService employeeService, EmployeeDTOMapper employeeDTOMapper, EmployeeProjectionService employeeProjectionService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeDTOMapper = employeeDTOMapper;
        this.employeeProjectionService = employeeProjectionService;
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/list")
    public ResponseEntity<?> getAllEmployees() {

        List<Employee> employees = employeeService.findAll();
        List<EmployeeDTO> employeeDTO = employeeDTOMapper.toListDTO(employees);
        List<EmployeeDTORequest> employeeDTORequests = employeeDTOMapper.toListDTOReguest(employeeDTO);

        return new ResponseEntity<>(employeeDTORequests, HttpStatus.OK);
    }


    @GetMapping("/fullNames")
    public List<EmployeeProjection> getEmployeeFullNames() {
        return employeeProjectionService.getEmployeeFullNames();
    }

    @GetMapping("/listEmployee")
    public List<EmployeeProjection> getAllEmployeeProjections() {
        return employeeProjectionService.getAllEmployeeProjections();
    }

    @ResponseBody
    @PostMapping("/pageEmployees")
    public Page<EmployeeDTO> getFilteredEmployees(@RequestBody FilterEmployeeDTO filterEmployeeDTO,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "employeeName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Pageable pageable = PageRequest.of(page, size);
        Specification<Employee> spec = EmployeeSpecification.buildSpecifications(filterEmployeeDTO, sortBy, sortDirection);
        Page<Employee> employeePage = employeeRepository.findAll(spec, pageable);

        return employeeDTOMapper.toPageDTO(employeePage);
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
            Employee employee = employeeDTOMapper.toEntity(employeeDTO);
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) throws  Exception {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee deleted successfully");
    }

}
