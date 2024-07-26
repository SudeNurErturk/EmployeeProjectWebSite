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
    public ResponseEntity getAllEmployees() {
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

//        Employee existingEmployee = employeeRepository.findForEmployeeId(id);
//            if (existingEmployee !=null) {
//               employeeDTO.setId(existingEmployee.getId());

//             employeeDTO.getOtherInformation().setId(existingEmployee.getId());
//           employeeDTO.getPersonalInformation().setId(existingEmployee.getPersonalInformation().getId());

                    Employee existingEmployee = employeeRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

                   //validateUniqueFieldsForUpdate(employeeDTO, existingEmployee);
                    existingEmployee.setEmployeeName(employeeDTO.getEmployeeName());
                    existingEmployee.setEmployeeSurname(employeeDTO.getEmployeeSurname());



                    existingEmployee.setLevel(Level.valueOf(String.valueOf(employeeDTO.getLevel())));
                    existingEmployee.setEmployeePhone(employeeDTO.getEmployeePhone());
                    existingEmployee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
                    existingEmployee.setBirthdate(employeeDTO.getBirthdate());
                    existingEmployee.setWorkingPlace(Enum.WorkingPlace.valueOf(String.valueOf(employeeDTO.getWorkingPlace())));
                    existingEmployee.setContractType(Enum.ContractType.valueOf(String.valueOf(employeeDTO.getContractType())));
                    existingEmployee.setTeam(Enum.Team.valueOf(String.valueOf(employeeDTO.getTeam())));
                    existingEmployee.setStartingDate(employeeDTO.getStartingDate());
                    existingEmployee.setEndingDate(employeeDTO.getEndingDate());


                    if (existingEmployee.getPersonalInformation() == null) {
                        existingEmployee.setPersonalInformation(new PersonalInformation());
                    }
                    PersonalInformationDTO personalInfoDTO = employeeDTO.getPersonalInformation();
                    existingEmployee.getPersonalInformation().setBirthdate(personalInfoDTO.getBirthdate());
                    existingEmployee.getPersonalInformation().setPersonalSocialSecurityNumber(personalInfoDTO.getPersonalSocialSecurityNumber());
                    existingEmployee.getPersonalInformation().setMilitaryService(personalInfoDTO.getMilitaryService());
                    existingEmployee.getPersonalInformation().setGender((personalInfoDTO.getGender()));
                    existingEmployee.getPersonalInformation().setMaritalStatus(personalInfoDTO.getMaritalStatus());


                    if (existingEmployee.getOtherInformation() == null) {
                        existingEmployee.setOtherInformation(new OtherInformation());
                    }
                    OtherInformationDTO otherInfoDTO = employeeDTO.getOtherInformation();
                    existingEmployee.getOtherInformation().setAddress(otherInfoDTO.getAddress());
                    existingEmployee.getOtherInformation().setBank(otherInfoDTO.getBank());
                    existingEmployee.getOtherInformation().setIban(otherInfoDTO.getIban());
                    existingEmployee.getOtherInformation().setEmergencyPersonName(otherInfoDTO.getEmergencyPersonName());
                    existingEmployee.getOtherInformation().setEmergencyPersonPhone(otherInfoDTO.getEmergencyPersonPhone());


//                    List<ProjectDTO> projects = employeeDTO.g();
//                    if (projects != null) {
//                        List<Project> updatedProjects = projects.stream()
//                                .map(proMapper::toProject)
//                                .collect(Collectors.toList());
//                        existingEmployee.setProjects(updatedProjects);
//                    }


                    Employee savedEmployee = employeeRepository.save(existingEmployee);


                    return employeeDTOMapper.toDTO(savedEmployee);

//          Employee employee = employeeDTOMapper.toEntity(employeeDTO);
//                Employee updatedEmployee = employeeService.updateEmployee(employee);
//                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
//
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
//            }
        }









    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) throws  Exception {

            Employee existingEmployee = employeeService.getEmployeeByEmployeeId(employeeId);
            if (existingEmployee  != null) {
                employeeService.deleteEmployeeById(employeeId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }

    }


}




