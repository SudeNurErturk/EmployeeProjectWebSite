package com.example.EmployeeWeb.OtherInfo.repository;

import com.example.EmployeeWeb.OtherInfo.model.OtherInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherInfoRepository extends JpaRepository<OtherInformation, Long> {


}
