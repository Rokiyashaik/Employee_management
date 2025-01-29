package com.example.EmployeeManagement.repository;

import com.example.EmployeeManagement.model.Department_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Department_repo extends JpaRepository<Department_entity,Integer> {

}
