package com.example.EmployeeWeb.Employee.Controller;


import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.EmployeeDTORequest;
import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;

import com.example.EmployeeWeb.Employee.mapper.EmployeeDTOMapper;
import com.example.EmployeeWeb.Employee.projection.EmployeeProjection;
import com.example.EmployeeWeb.Employee.projection.EmployeeProjectionService;
import com.example.EmployeeWeb.Employee.repository.EmployeeRepository;
import com.example.EmployeeWeb.Employee.service.EmployeeService;
import com.example.EmployeeWeb.Employee.model.Employee;

import com.example.EmployeeWeb.Employee.specification.*;
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


import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeProjectionService employeeProjectionService;




    public EmployeeController(EmployeeService employeeService, EmployeeDTOMapper employeeDTOMapper, EmployeeProjectionService employeeProjectionService) {
        this.employeeService = employeeService;
        this.employeeDTOMapper = employeeDTOMapper;

        this.employeeProjectionService = employeeProjectionService;
    }

    @GetMapping("/list")
    public ResponseEntity getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        List<EmployeeDTO> employeeDTO= employeeDTOMapper.toListDTO(employees);
        List<EmployeeDTORequest> employeeDTORequests = employeeDTOMapper.toListDTOReguest(employeeDTO);
        return new ResponseEntity<>(employeeDTORequests, HttpStatus.OK);
    }


    @GetMapping("/fullNames")
    public List<EmployeeProjection> getEmployeeFullNames() {
        List<EmployeeProjection> employeeProjection = employeeProjectionService.getEmployeeFullNames();
        return employeeProjection;
    }


//    @ResponseBody
//    @PostMapping("/search")

//
//    public List<FilterEmployeeDTO> searchEmployees(@RequestParam String search) {
//        EmployeeSpecificationsBuilder builder = new EmployeeSpecificationsBuilder();
//        String[] split = URLDecoder.decode(search).split(":");
//        for (String s : split) {
//            System.out.println(s);
//
//        }
//        return null;
//    }



//    public List<FilterEmployeeDTO> searchEmployees(@RequestBody EmployeeSearchRequest request) {
//        EmployeeSpecificationsBuilder builder = new EmployeeSpecificationsBuilder();
//        for (SpecSearchCriteria criteria : request.getSearchCriteriaList()) {
//            builder.with(criteria.getKey(), criteria.getOperation(), criteria.getValue());
//        }
//
//        Specification<Employee> spec = builder.build();
//
//        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortBy());
//
//        List<Employee> employees = employeeService.getEmployees(spec, sort);
//
//        List<EmployeeDTO> employeeDTOs = employeeDTOMapper.toListDTO(employees);
//
//        return employeeDTOMapper.dtoToFilter(employeeDTOs);
//
//    }

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

//        EmployeeSpecificationsBuilder builder = new EmployeeSpecificationsBuilder();
//        employeeSpecification.buildSpecifications(builder,filterRequest);
//        Specification<Employee> spec = builder.build();
//        String sortBy = filterRequest.getSortBy() != null ? filterRequest.getSortBy() : "id";
//        Sort.Direction sortDirection = "DESC".equalsIgnoreCase(filterRequest.getSortDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
//        Sort sort = Sort.by(sortDirection, sortBy);
//        List<Employee> employees = employeeService.getEmployees(spec, sort);
//        return employeeService.convertToDTORequestList(employees);





//API tarafından HTTP yanıtlarını daha kolay takibi sağlar)

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {

            Employee employee = employeeDTOMapper.toEntity(employeeDTO);
            System.out.println("Entity: " + employee.toString());
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
            // return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        try {
            Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
            if (existingEmployee.isPresent()) {
               employeeDTO.setId(id);

                Employee employee = employeeDTOMapper.toEntity(employeeDTO);
                Employee updatedEmployee = employeeService.updateEmployee(employee);
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        try {
            Optional<Employee> existingEmployee = employeeService.getEmployeeByEmployeeId(employeeId);
            if (existingEmployee.isPresent()) {
                employeeService.deleteEmployeeById(employeeId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}




