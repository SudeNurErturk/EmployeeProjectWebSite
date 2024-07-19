package com.example.EmployeeWeb.Employee.specification;

import com.example.EmployeeWeb.Employee.model.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSpecificationsBuilder {

        private final List<SpecSearchCriteria> params;

        public EmployeeSpecificationsBuilder() {
            params = new ArrayList<>();
        }

    public EmployeeSpecificationsBuilder(List<SpecSearchCriteria> params) {
        this.params = params;
    }

    public final EmployeeSpecificationsBuilder with(String key, String operation, Object value,
                                                    String prefix, String suffix) {
            return with(null, key, operation, value, prefix, suffix);
        }

        public final EmployeeSpecificationsBuilder with(String orPredicate, String key, String operation,
                                                        Object value, String prefix, String suffix) {
            SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));

            /*
            if (op != null) {
                if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                    boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                    boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                    if (startWithAsterisk && endWithAsterisk) {
                        op = SearchOperation.CONTAINS;
                    } else if (startWithAsterisk) {
                        op = SearchOperation.ENDS_WITH;
                    } else if (endWithAsterisk) {
                        op = SearchOperation.STARTS_WITH;
                    }
                }
                */
            if (op != null) {
                if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                    boolean startWithAsterisk = prefix != null &&
                    prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                    boolean endWithAsterisk = suffix != null &&
                            suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                    if (startWithAsterisk && endWithAsterisk) {
                        op = SearchOperation.CONTAINS;
                    } else if (startWithAsterisk) {
                        op = SearchOperation.ENDS_WITH;
                    } else if (endWithAsterisk) {
                        op = SearchOperation.STARTS_WITH;
                    }
                }
                params.add(new SpecSearchCriteria(key, op, orPredicate, value));
            }
            return this;
        }


        public Specification<Employee> build() {
            if (params.size() == 0)
                return null;

            Specification result = new EmployeeSpecification(params.get(0));

            for (int i = 1; i < params.size(); i++) {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(new EmployeeSpecification(params.get(i)))
                        : Specification.where(result).and(new EmployeeSpecification(params.get(i)));
            }

 /*
            List<Specification<Employee>> specs = params.stream()
                    .map(EmployeeSpecification::new)
                    .collect(Collectors.toList());

            Specification<Employee> result = specs.get(0);

            for (int i = 1; i < specs.size(); i++) {
                result = params.get(i).isOrPredicate()
                        ? Specification.where(result).or(specs.get(i))
                        : Specification.where(result).and(specs.get(i));
            }*/
            return result;

        }
    }

