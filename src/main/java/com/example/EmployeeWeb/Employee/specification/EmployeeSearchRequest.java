package com.example.EmployeeWeb.Employee.specification;

import java.util.List;

public class EmployeeSearchRequest {
    private List<SpecSearchCriteria> searchCriteriaList;
    private String sortBy;
    private String sortDirection; // ASC or DESC

    // Getter and Setter methods

    public List<SpecSearchCriteria> getSearchCriteriaList() {
        return searchCriteriaList;
    }

    public void setSearchCriteriaList(List<SpecSearchCriteria> searchCriteriaList) {
        this.searchCriteriaList = searchCriteriaList;
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
