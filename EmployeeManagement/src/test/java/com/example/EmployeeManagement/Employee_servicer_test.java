package com.example.EmployeeManagement;

import com.example.EmployeeManagement.model.Department_entity;
import com.example.EmployeeManagement.model.Employee_entity;
import com.example.EmployeeManagement.repository.Department_repo;
import com.example.EmployeeManagement.repository.Employee_repo;
import com.example.EmployeeManagement.servicer.Employee_Servicer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Employee_servicer_test {
    @Mock
    private Employee_repo employee_repo;

    @Mock
    private Department_repo dept_repo;

    @InjectMocks
    private Employee_Servicer employee_servicer;

    private Employee_entity employee_entity;
    private Department_entity department_entity;
    @BeforeEach
    void settingup(){
        MockitoAnnotations.openMocks(this);
        employee_entity=new Employee_entity();
        department_entity=new Department_entity();
        employee_entity.setEmp_id(1);
        employee_entity.setEmp_name("Rokiya");
        employee_entity.setEmp_gender("female");
        employee_entity.setEmp_phone(8125830064L);
        employee_entity.setEmp_email("rokiyashaik@gamil.com");
        employee_entity.setEmp_age(21);
        employee_entity.setEmp_salary(200000L);
        department_entity.setDept_id(1023);
        department_entity.setDept_name("P2M");
        employee_entity.setDepartment(department_entity);
        employee_entity.setDept_name(department_entity.getDept_name());


    }

    @Test
    void adding_employee_test(){
        when(dept_repo.findById(department_entity.getDept_id())).thenReturn(Optional.of(department_entity));
        when(employee_repo.save(employee_entity)).thenReturn(employee_entity);
        Employee_entity result=employee_servicer.adding_employees(employee_entity);
        assertNotNull(result);
        assertEquals(employee_entity.getEmp_name(), result.getEmp_name());
        assertEquals(employee_entity.getEmp_id(), result.getEmp_id());
        assertEquals(employee_entity.getEmp_salary(), result.getEmp_salary());
        assertEquals(employee_entity.getDepartment().getDept_name(), result.getDepartment().getDept_name());
        verify(employee_repo, times(1)).save(employee_entity);
    }
    @Test
    void getallEmp_test(){
        when(employee_repo.findAll()).thenReturn(List.of(employee_entity));
        var result=employee_servicer.get_employees();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        verify(employee_repo,times(1)).findAll();
    }
    @Test
    void getempbyid_test(){
        when(employee_repo.findById(1)).thenReturn(Optional.of(employee_entity));
        Employee_entity result_id=employee_servicer.get_empdata_byid(1);
        assertEquals(employee_entity.getEmp_name(),result_id.getEmp_name());
        verify(employee_repo, times(1)).findById(1);
    }

    @Test
    void getempbyid_test_fail(){
        when(employee_repo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                employee_servicer.get_empdata_byid(1)
        );

        assertEquals("Cannot find user with id: 1", exception.getMessage());
        verify(employee_repo, times(1)).findById(1);
    }



    @Test
    public void testUpdateEmployee() {
        Employee_entity updatedEmployee = new Employee_entity();
        updatedEmployee.setEmp_name("basera");
        updatedEmployee.setEmp_age(21);

        when(employee_repo.findById(1)).thenReturn(Optional.of(employee_entity));
        when(employee_repo.save(any(Employee_entity.class))).thenReturn(updatedEmployee);

        Employee_entity result = employee_servicer.update_emp(updatedEmployee, 1);

        assertNotNull(result);
        assertEquals("basera", result.getEmp_name());
        assertEquals(21, result.getEmp_age());
    }

    @Test
    public void testUpdateEmployeeNotFound() {
        Employee_entity updatedEmployee = new Employee_entity();
        updatedEmployee.setEmp_name("basera");

        when(employee_repo.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employee_servicer.update_emp(updatedEmployee, 1);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
    }

    @Test
    public void testPatchEmployee() {
        Employee_entity updatedEmployee = new Employee_entity();
        updatedEmployee.setEmp_name("basera");

        when(employee_repo.findById(1)).thenReturn(Optional.of(employee_entity));
        when(employee_repo.save(any(Employee_entity.class))).thenReturn(updatedEmployee);

        Employee_entity result = employee_servicer.patch_emp(1, updatedEmployee);

        assertNotNull(result);
        assertEquals("basera", result.getEmp_name());
    }

    @Test
    public void testPatchEmployeeNotFound() {
        Employee_entity updatedEmployee = new Employee_entity();
        updatedEmployee.setEmp_name("basera");

        when(employee_repo.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employee_servicer.patch_emp(1, updatedEmployee);
        });

        assertEquals("Employee with ID 1 not found", exception.getMessage());
    }





    @Test
    void del_emp_testSuccss() {
        when(employee_repo.existsById(1)).thenReturn(true);

        String result = employee_servicer.delete_emp(1);

        assertEquals("user is deleted", result);
        verify(employee_repo, times(1)).deleteById(1);
    }






    @Test
    void del_emp_testfailure(){
        when(employee_repo.existsById(1)).thenReturn(false);
        String result = employee_servicer.delete_emp(1);

        assertEquals("no user found", result);
        verify(employee_repo, times(0)).deleteById(1);
    }




}
