package com.example.EmployeeWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.PreparedStatement;

@SpringBootApplication
public class EmployeeWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeWebApplication.class, args);
	}

}
