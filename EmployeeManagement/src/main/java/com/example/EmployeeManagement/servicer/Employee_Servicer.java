package com.example.EmployeeManagement.servicer;

import com.example.EmployeeManagement.model.Department_entity;
import com.example.EmployeeManagement.model.Employee_entity;
import com.example.EmployeeManagement.repository.Department_repo;
import com.example.EmployeeManagement.repository.Employee_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Employee_Servicer {


    private Employee_repo emp_repo;
    private Department_repo dept_repo;
    @Autowired
    public Employee_Servicer(Employee_repo emp_repo, Department_repo dept_repo){
        this.emp_repo=emp_repo;
        this.dept_repo= dept_repo;
    }

    public Employee_entity adding_employees(Employee_entity emp) {
        if (emp.getDepartment() == null || emp.getDepartment().getDept_id() == 0) {
            throw new IllegalArgumentException("Department details are incomplete for the employee");
        }
        Department_entity department = dept_repo.findById(emp.getDepartment().getDept_id())
                .orElseThrow(() -> new RuntimeException("Department with ID " + emp.getDepartment().getDept_id() + " cannot be found"));

        emp.setDepartment(department);

        emp.setDept_name(department.getDept_name());



        return emp_repo.save(emp);
    }


    public List<Employee_entity> get_employees(){
        return  emp_repo.findAll();
    }

    public Employee_entity get_empdata_byid(Integer id) {
        return emp_repo.findById(id).orElseThrow(() -> new RuntimeException("Cannot find user with id: " + id));
    }

    public Employee_entity update_emp(Employee_entity new_emp,Integer id){
        return emp_repo.findById(id).map(p-> {
            p.setEmp_name(new_emp.getEmp_name());
            p.setEmp_age(new_emp.getEmp_age());
            p.setEmp_gender(new_emp.getEmp_gender());
            p.setEmp_phone(new_emp.getEmp_phone());
            p.setEmp_email(new_emp.getEmp_email());
            p.setEmp_salary(new_emp.getEmp_salary());
            return emp_repo.save(p);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Employee_entity patch_emp(Integer id, Employee_entity updatedEmp) {
        Employee_entity existingEmp = emp_repo.findById(id).orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));

        if (updatedEmp.getEmp_name() != null) {
            existingEmp.setEmp_name(updatedEmp.getEmp_name());
        }
        if (updatedEmp.getEmp_age() != 0) {
            existingEmp.setEmp_age(updatedEmp.getEmp_age());
        }
        if (updatedEmp.getEmp_gender() != null) {
            existingEmp.setEmp_gender(updatedEmp.getEmp_gender());
        }
        if (updatedEmp.getEmp_email() != null) {
            existingEmp.setEmp_email(updatedEmp.getEmp_email());
        }
        if (updatedEmp.getEmp_phone() != null) {
            existingEmp.setEmp_phone(updatedEmp.getEmp_phone());
        }
        if (updatedEmp.getEmp_salary() != null) {
            existingEmp.setEmp_salary(updatedEmp.getEmp_salary());
        }

        if (updatedEmp.getDepartment() != null) {
            if (updatedEmp.getDepartment().getDept_id() != 0) {
                Department_entity department = dept_repo.findById(updatedEmp.getDepartment().getDept_id())
                        .orElseThrow(() -> new RuntimeException("Department with ID " + updatedEmp.getDepartment().getDept_id() + " cannot be found"));
                existingEmp.setDepartment(department);
                existingEmp.setDept_name(department.getDept_name());
            } else {
                throw new IllegalArgumentException("Department details are incomplete for the employee");
            }
        }

        return emp_repo.save(existingEmp);
    }
    public String delete_emp(Integer id){
        if(!emp_repo.existsById(id)){
            return "no user found";
        }
        else{
            emp_repo.deleteById(id);
            return "user is deleted";
        }
    }

}

