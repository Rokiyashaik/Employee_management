package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.model.Department_entity;
import com.example.EmployeeManagement.servicer.Department_Servicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Department")
public class Department_Controller {
    private Department_Servicer dept_service;
    @Autowired

    public Department_Controller(Department_Servicer dept_service){
        this.dept_service=dept_service;
    }

    @PostMapping("/addingdeptartment")
    public Department_entity adddept(@RequestBody Department_entity new_dept){
        return dept_service.add_dept(new_dept);
    }

    @GetMapping("/alldepartment")
    public List<Department_entity> alldept(){
        return dept_service.get_alldept();
    }

    @GetMapping("/getdepartmentbyid/{id}")
    public Department_entity user_byid(@PathVariable Integer id){
        return dept_service.get_deptbyid(id);
    }

    @PutMapping("/update_department_put/{id}")
    public Department_entity update_byid(@RequestBody Department_entity new_dept,@PathVariable Integer id){
        return dept_service.update_dept(new_dept,id);
    }

    @PatchMapping("/update_dept_patch/{id}")
    public ResponseEntity<Department_entity> patchDepartment(@PathVariable Integer id, @RequestBody Department_entity updatedDept) {
        Department_entity updatedDepartment = dept_service.patch_dept(id, updatedDept);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }
    @DeleteMapping("/delete_dept/{id}")
    public String delete_emp(@PathVariable Integer id){
        return dept_service.delete_dept(id);
    }

}
