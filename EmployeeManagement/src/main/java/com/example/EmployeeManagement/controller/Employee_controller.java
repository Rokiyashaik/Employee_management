package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.model.Employee_entity;
import com.example.EmployeeManagement.servicer.Employee_Servicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Employee")
public class Employee_controller {
    private Employee_Servicer emp_service;
    @Autowired
    public Employee_controller(Employee_Servicer emp_service){
        this.emp_service=emp_service;
    }


    @PostMapping("/addemployees")
    public Employee_entity addusers(@RequestBody Employee_entity emp){
        return emp_service.adding_employees((emp));

    }
    @GetMapping("/allemployees")
    public List<Employee_entity> allusers(){
        return emp_service.get_employees();
    }

    @GetMapping("/getuserbyid/{id}")
    public Employee_entity user_byid(@PathVariable Integer id){
        return emp_service.get_empdata_byid(id);
    }

    @PutMapping("/update_emp_put/{id}")
    public Employee_entity update_byid(@RequestBody Employee_entity new_emp,@PathVariable Integer id){
        return emp_service.update_emp(new_emp,id);
    }
    @PatchMapping("/update_emp_patch/{id}")
    public ResponseEntity<Employee_entity> patchEmployee(@PathVariable Integer id, @RequestBody Employee_entity updatedEmp) {
        Employee_entity updatedEmployee = emp_service.patch_emp(id, updatedEmp);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete_emp/{id}")
    public String delete_emp(@PathVariable Integer id){
        return emp_service.delete_emp(id);
    }

}
