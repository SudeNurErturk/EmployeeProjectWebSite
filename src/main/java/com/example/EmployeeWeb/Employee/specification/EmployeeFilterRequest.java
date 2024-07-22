package com.example.EmployeeWeb.Employee.specification;

import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;

import java.util.List;

public class EmployeeFilterRequest {
    private List<FilterEmployeeDTO> employee;
    private String sortBy;
    private String sortDirection;


    public List<FilterEmployeeDTO> getEmployee() {
        return employee;
    }

    public void setEmployee(List<FilterEmployeeDTO> employee) {
        this.employee = employee;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
