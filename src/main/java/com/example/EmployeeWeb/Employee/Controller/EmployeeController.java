package com.example.EmployeeWeb.Employee.Controller;


import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.EmployeeDTORequest;
import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;

import com.example.EmployeeWeb.Employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.Employee.model.Enum;
import com.example.EmployeeWeb.Employee.model.Level;
import com.example.EmployeeWeb.Employee.projection.EmployeeProjection;
import com.example.EmployeeWeb.Employee.projection.EmployeeProjectionService;
import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;
import com.example.EmployeeWeb.Employee.service.EmployeeService;
import com.example.EmployeeWeb.Employee.model.Employee;

import com.example.EmployeeWeb.Employee.specification.*;
import com.example.EmployeeWeb.OtherInfo.DTO.OtherInformationDTO;
import com.example.EmployeeWeb.OtherInfo.model.OtherInformation;
import com.example.EmployeeWeb.PersonalInformation.DTO.PersonalInformationDTO;
import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import com.example.EmployeeWeb.Project.DTO.ProjectDTO;
import com.example.EmployeeWeb.Project.model.Project;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
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
import java.util.Optional;



@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeProjectionService employeeProjectionService;
    private final EmployeeRepository employeeRepository;


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
        List<EmployeeProjection> employeeProjection = employeeProjectionService.getEmployeeFullNames();
        return employeeProjection;
    }
    @GetMapping("/listEmployee")
    public List<EmployeeProjection> getAllEmployeeProjections() {
        return employeeProjectionService.getAllEmployeeProjections();
    }


    @ResponseBody
    @PostMapping("/filter")
    public List<EmployeeDTORequest> filterEmployees(@RequestBody EmployeeFilterRequest filterRequest) {

        Specification<Employee> spec = EmployeeSpecification.buildSpecifications(filterRequest.getEmployee());
        String sortBy = filterRequest.getSortBy() != null ? filterRequest.getSortBy() : "id";
        Sort.Direction sortDirection = "DESC".equalsIgnoreCase(filterRequest.getSortDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortBy);

        List<Employee> employees = employeeService.getEmployees(spec, sort);
        return employeeService.convertToDTORequestList(employees);
    }


    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

            Employee employee = employeeDTOMapper.toEntity(employeeDTO);

            System.out.println("Entity: " + employee.toString());
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
            // return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }


    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) throws  Exception {
        return employeeService.updateEmployee(id, employeeDTO);
    }







    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) throws  Exception {
                 employeeService.deleteEmployeeById(employeeId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee deleted successfully");
            }



}




