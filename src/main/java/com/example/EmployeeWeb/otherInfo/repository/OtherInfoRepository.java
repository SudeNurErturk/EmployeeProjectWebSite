package com.example.EmployeeWeb.otherInfo.repository;

import com.example.EmployeeWeb.otherInfo.model.OtherInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherInfoRepository extends JpaRepository<OtherInformation, Long> {


}
