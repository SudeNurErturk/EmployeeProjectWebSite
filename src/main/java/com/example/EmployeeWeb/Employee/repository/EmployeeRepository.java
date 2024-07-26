package com.example.EmployeeWeb.Employee.repository;

import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.Employee.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> , JpaSpecificationExecutor<Employee> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.employeeEmail = :employeeEmail")
    boolean findByEmployeeEmail(@Param("employeeEmail")String employeeEmail);



    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.employeePhone = :employeePhone")
    boolean findByEmployeePhone(@Param("employeePhone")String employeePhone);

    @Query("SELECT e FROM Employee e WHERE e.id = :id AND e.otherInformation.id = :id AND e.personalInformation.id= :id")
    Employee findForEmployeeId(Long id);



    @Query("SELECT CONCAT(e.employeeName, ' ', e.employeeSurname) AS fullName FROM Employee e")
    List<EmployeeProjection> findEmployeeFullNames();
    @Query("SELECT e FROM Employee e")
    List<EmployeeProjection> findAllEmployees();

}
