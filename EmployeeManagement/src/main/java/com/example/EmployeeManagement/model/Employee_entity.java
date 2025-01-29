package com.example.EmployeeManagement.model;

import jakarta.persistence.*;


@Entity

public class Employee_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;
    private String emp_name;
    private int emp_age;
    private String emp_gender;
    private String emp_email;
    private Long emp_phone;
    private Long emp_salary;



    @ManyToOne
    @JoinColumn(name="dept_id",nullable = false)
    private  Department_entity department;


    private String dept_name;
    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
    public String getDept_name() {
        return dept_name;
    }






    public Department_entity getDepartment() {
        return department;
    }

    public void setDepartment(Department_entity department) {
        this.department = department;
    }



    public Employee_entity(){

    }

    public Employee_entity(int emp_id, String emp_name, int emp_age, String emp_gender, String emp_email, Long emp_phone, Long emp_salary, Department_entity department, String dept_name) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_age = emp_age;
        this.emp_gender = emp_gender;
        this.emp_email = emp_email;
        this.emp_phone = emp_phone;
        this.emp_salary = emp_salary;
        this.department = department;

    }

    public int getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public int getEmp_age() {
        return emp_age;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public Long getEmp_phone() {
        return emp_phone;
    }

    public Long getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setEmp_age(int emp_age) {
        this.emp_age = emp_age;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public void setEmp_phone(Long emp_phone) {
        this.emp_phone = emp_phone;
    }

    public void setEmp_salary(Long emp_salary) {
        this.emp_salary = emp_salary;
    }




    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + emp_id+
                ", empName='" + emp_name + '\'' +
                ", empGender='" + emp_gender + '\'' +
                ", empAge=" + emp_age +
                ", empPhone=" + emp_phone +
                ", empSalary=" + emp_salary +
                ", empGmail='" + emp_email + '\'' +
                ",department='"+department +'\''+
                '}';
    }

}
