package com.example.EmployeeWeb.PersonalInformation.repository;


import com.example.EmployeeWeb.PersonalInformation.model.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInformation, Long> {
}
