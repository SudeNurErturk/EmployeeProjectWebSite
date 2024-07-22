package com.example.EmployeeWeb.Employee.specification;

import com.example.EmployeeWeb.Employee.DTO.EmployeeDTO;
import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;
import com.example.EmployeeWeb.Employee.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;



public class EmployeeSpecification implements Specification<Employee> {
    private SpecSearchCriteria criteria;



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



}
