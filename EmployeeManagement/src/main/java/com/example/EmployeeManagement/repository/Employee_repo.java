package com.example.EmployeeManagement.repository;

import com.example.EmployeeManagement.model.Employee_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Employee_repo extends JpaRepository<Employee_entity, Integer> {


}
