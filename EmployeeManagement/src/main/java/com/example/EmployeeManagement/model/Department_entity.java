package com.example.EmployeeManagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dept_id;
    private String dept_name;
    private String dept_function;
    private  Long dept_code;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee_entity> employees;
    public List<Employee_entity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee_entity> employees) {
        this.employees = employees;
    }



    public Department_entity(){

    }


    public Department_entity(int dept_id, String dept_name, String dept_function, Long dept_code) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.dept_function = dept_function;
        this.dept_code = dept_code;
    }

    public String getDept_function() {
        return dept_function;
    }

    public void setDept_function(String dept_function) {
        this.dept_function = dept_function;
    }

    public Long getDept_code() {
        return dept_code;
    }

    public void setDept_code(Long dept_code) {
        this.dept_code = dept_code;
    }



    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }


    @Override
    public String toString() {
        return "Department{" +
                "deptid=" + dept_id+
                ", deptname='" + dept_name + '\'' +
                ", deptfunction='" + dept_function + '\'' +
                ", deptcode='" + dept_code + '\'' +

                '}';
    }
}

