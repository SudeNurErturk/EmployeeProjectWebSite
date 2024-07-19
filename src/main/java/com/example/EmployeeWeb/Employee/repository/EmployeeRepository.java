package com.example.EmployeeWeb.Employee.repository;

import com.example.EmployeeWeb.Employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> , JpaSpecificationExecutor<Employee> {
    Optional<Employee> findByEmployeeEmail(String emailAddress);
    Optional<Employee> findByEmployeePhone(String employeePhone);
    Optional<Employee> findById(Long id);

}
