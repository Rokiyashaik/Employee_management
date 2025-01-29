package com.example.EmployeeManagement.servicer;

import com.example.EmployeeManagement.model.Department_entity;
import com.example.EmployeeManagement.repository.Department_repo;
import com.example.EmployeeManagement.repository.Employee_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Department_Servicer {
    private Department_repo dept_repo;
    private Employee_repo employee_repo;
    @Autowired
    public Department_Servicer(Department_repo dept_repo,Employee_repo employee_repo){
        this.dept_repo=dept_repo;
        this.employee_repo=employee_repo;
    }

    public Department_entity add_dept(Department_entity new_dept){
        return dept_repo.save(new_dept);
    }

    public List<Department_entity> get_alldept(){
        return dept_repo.findAll();
    }
    public Department_entity get_deptbyid(Integer id){
        return dept_repo.findById(id).orElseThrow(()->new RuntimeException("department not found with id "+ id));
    }


    public Department_entity update_dept(Department_entity new_dept,Integer id){
        return dept_repo.findById(id).map(p->{
            p.setDept_name(new_dept.getDept_name());
            p.setDept_code(new_dept.getDept_code());
            p.setDept_function(new_dept.getDept_function());
            return dept_repo.save(p);
        }).orElseThrow(() -> new RuntimeException("department with id "+id+" not found"));
    }

    public Department_entity patch_dept(Integer id, Department_entity updatedDept) {
        Optional<Department_entity> existingDeptOpt = dept_repo.findById(id);
        if (existingDeptOpt.isPresent()) {
            Department_entity existingDept = existingDeptOpt.get();


            if (updatedDept.getDept_name() != null) {
                existingDept.setDept_name(updatedDept.getDept_name());
            }
            if (updatedDept.getDept_code() != null) {
                existingDept.setDept_code(updatedDept.getDept_code());
            }
            if (updatedDept.getDept_function() != null) {
                existingDept.setDept_function(updatedDept.getDept_function());
            }


            return dept_repo.save(existingDept);
        } else {
            throw new RuntimeException("Department with ID " + id + " not found");
        }
    }
    public String delete_dept(int id){
        if(!dept_repo.existsById(id)){
            return "Department with id " + id + " not found";
        } else {
            Department_entity department = dept_repo.findById(id).get();

            dept_repo.deleteById(id);
            dept_repo.delete(department);
            return "Department with id " + id + " and all its employees have been deleted";
        }
    }




}