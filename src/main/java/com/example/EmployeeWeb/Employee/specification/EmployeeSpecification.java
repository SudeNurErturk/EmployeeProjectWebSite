package com.example.EmployeeWeb.Employee.specification;

import com.example.EmployeeWeb.Employee.DTO.FilterEmployeeDTO;
import com.example.EmployeeWeb.Employee.model.Employee;
import com.example.EmployeeWeb.enums.Enum;
import com.example.EmployeeWeb.Employee.model.Level;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


public class EmployeeSpecification  {
    private final SpecSearchCriteria criteria;



    public EmployeeSpecification(SpecSearchCriteria criteria) {
        this.criteria = criteria;
    }


//        switch (criteria.getOperation()) {
//            case EQUALITY:
//                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
//            case NEGATION:
//                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
//            case GREATER_THAN:
//                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
//            case LESS_THAN:
//                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
//            case LIKE:
//                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
//
//            default:
//                return null;
//        }

        public static Specification<Employee> buildSpecifications(FilterEmployeeDTO filterEmployeeDTO,String sortBy, String sortDirection) {
            return new  Specification<Employee>() {
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                //EmployeeSpecificationsBuilder builder = new EmployeeSpecificationsBuilder();
                Predicate predicate = builder.conjunction();
                if (filterEmployeeDTO.getEmployeeName() != null) {
                    predicate = builder.and(predicate, builder.like(builder.lower(root.get("employeeName")), "%" + filterEmployeeDTO.getEmployeeName().toLowerCase() + "%"));
                }

                if (filterEmployeeDTO.getEmployeeSurname() != null) {
                    predicate = builder.and(predicate, builder.like(builder.lower(root.get("employeeSurname")), "%" + filterEmployeeDTO.getEmployeeSurname().toLowerCase() + "%"));
                }

                if (filterEmployeeDTO.getEmployeePhone() != null) {
                    predicate = builder.and(predicate, builder.equal(root.get("employeePhone"), filterEmployeeDTO.getEmployeePhone()));
                }

                if (filterEmployeeDTO.getEmployeeEmail() != null) {
                    predicate = builder.and(predicate, builder.equal(root.get("email"), filterEmployeeDTO.getEmployeeEmail()));
                }

                if (filterEmployeeDTO.getBirthdate() != null) {
                    predicate = builder.and(predicate, builder.equal(root.get("birthdate"), filterEmployeeDTO.getBirthdate()));
                }

                if (filterEmployeeDTO.getLevel() != null) {
                    Level level = Level.valueOf(String.valueOf(filterEmployeeDTO.getLevel()));
                    predicate = builder.and(predicate, builder.equal(root.get("level"), level));
                }

                if (filterEmployeeDTO.getWorkingPlace() != null) {
                    Enum.WorkingPlace workingPlace = Enum.WorkingPlace.valueOf(String.valueOf(filterEmployeeDTO.getWorkingPlace()));
                    predicate = builder.and(predicate, builder.equal(root.get("workingPlace"), workingPlace));
                }

                if (filterEmployeeDTO.getContractType() != null) {
                    Enum.ContractType contractType = Enum.ContractType.valueOf(String.valueOf(filterEmployeeDTO.getContractType()));
                    predicate = builder.and(predicate, builder.equal(root.get("contractType"), contractType));
                }

                if (filterEmployeeDTO.getTeam() != null) {
                    Enum.Team team = Enum.Team.valueOf(String.valueOf(filterEmployeeDTO.getTeam()));
                    predicate = builder.and(predicate, builder.equal(root.get("team"), team));
                }

                if (filterEmployeeDTO.getStartingDate() != null) {
                    predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("startingDate"), filterEmployeeDTO.getStartingDate()));
                }

                if (filterEmployeeDTO.getEndingDate() != null) {
                    predicate = builder.and(predicate, builder.lessThanOrEqualTo(root.get("endDate"), filterEmployeeDTO.getEndingDate()));
                }


                if (sortBy != null && sortDirection != null) {
                    if (sortDirection.equalsIgnoreCase("asc")) {
                        query.orderBy(builder.asc(root.get(sortBy)));
                    } else if (sortDirection.equalsIgnoreCase("desc")) {
                        query.orderBy(builder.desc(root.get(sortBy)));
                    }
                }
return predicate;
            }

    };

    }
    }



