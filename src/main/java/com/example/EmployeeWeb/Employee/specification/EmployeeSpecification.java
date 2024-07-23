package com.example.EmployeeWeb.Employee.specification;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;
import com.example.EmployeeWeb.Employee.model.Employee;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;


public class EmployeeSpecification implements Specification<Employee> {
    private final SpecSearchCriteria criteria;



    public EmployeeSpecification(SpecSearchCriteria criteria) {
        this.criteria = criteria;
    }

    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
//            case STARTS_WITH:
//                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
//            case ENDS_WITH:
//                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
//            case CONTAINS:
//                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
//
//
            default:
                return null;
        }


    }



    public static Specification<Employee> buildSpecifications(List<FilterEmployeeDTO>  employeeList) {
        EmployeeSpecificationsBuilder builder = new EmployeeSpecificationsBuilder();

        for (FilterEmployeeDTO dto : employeeList) {
            if (dto.getEmployeeName() != null ) {
                builder.with("employeeName", SearchOperation.getSimpleOperation(':'), dto.getEmployeeName());
            }
            if (dto.getEmployeeSurname() != null ) {
                builder.with("employeeSurname", SearchOperation.getSimpleOperation(':'), dto.getEmployeeSurname());
            }
            if (dto.getEmployeePhone() != null  ) {
                builder.with("employeePhone", SearchOperation.getSimpleOperation(':'), dto.getEmployeePhone());
            }
            if (dto.getEmployeeEmail() != null ) {
                builder.with("employeeEmail", SearchOperation.getSimpleOperation(':'), dto.getEmployeeEmail());
            }
            if (dto.getContractType() != null ) {
                builder.with("contractType", SearchOperation.getSimpleOperation(':'), dto.getContractType());
            }
            if (dto.getEndingDate() != null  ) {
                builder.with("endingDate", SearchOperation.getSimpleOperation(':'), dto.getEndingDate());
            }
            if (dto.getStartingDate() != null) {
                builder.with("startingDate", SearchOperation.getSimpleOperation(':'), dto.getStartingDate());
            }
            if (dto.getLevel() != null ) {
                builder.with("level", SearchOperation.getSimpleOperation(':'), dto.getLevel());
            }
            if (dto.getTeam() != null) {
                builder.with("team", SearchOperation.getSimpleOperation(':'), dto.getTeam());
            }
        }
        return builder.build();


    }



}
