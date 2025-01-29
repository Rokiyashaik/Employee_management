package com.example.EmployeeManagement;

import com.example.EmployeeManagement.model.Department_entity;
import com.example.EmployeeManagement.repository.Department_repo;
import com.example.EmployeeManagement.repository.Employee_repo;
import com.example.EmployeeManagement.servicer.Department_Servicer;
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
public class Department_Servicer_test {

    @Mock
    private Department_repo dept_repo;

    @Mock
    private Employee_repo employee_repo;

    @InjectMocks
    private Department_Servicer department_servicer;

    private Department_entity department;

    @BeforeEach
    public void settingUp() {
        MockitoAnnotations.openMocks(this);

        department = new Department_entity();
        department.setDept_id(1);
        department.setDept_name("Ecommerce");
        department.setDept_code(101L);
        department.setDept_function("website");
    }

    @Test
    public void testadd_dept() {
        when(dept_repo.save(department)).thenReturn(department);

        Department_entity result = department_servicer.add_dept(department);

        assertNotNull(result);
        assertEquals(department.getDept_id(), result.getDept_id());
        assertEquals(department.getDept_name(), result.getDept_name());
        verify(dept_repo, times(1)).save(department);
    }

    @Test
    public void testgetAlldept() {
        when(dept_repo.findAll()).thenReturn(List.of(department));

        var result = department_servicer.get_alldept();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(dept_repo, times(1)).findAll();
    }
    @Test
    public void testgetdeptById() {
        when(dept_repo.findById(1)).thenReturn(Optional.of(department));

        Department_entity result = department_servicer.get_deptbyid(1);

        assertNotNull(result);
        assertEquals(department.getDept_id(), result.getDept_id());
        verify(dept_repo, times(1)).findById(1);
    }
    @Test
    public void testdeptid_notfound() {
        when(dept_repo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            department_servicer.get_deptbyid(1);
        });

        assertEquals("department not found with id 1", exception.getMessage());
    }

    @Test
    public void testUpdateDepartment() {
        Department_entity updatedDept = new Department_entity();
        updatedDept.setDept_name("p2m");
        updatedDept.setDept_function("product to market");
        updatedDept.setDept_code(102L);

        when(dept_repo.findById(1)).thenReturn(Optional.of(department));
        when(dept_repo.save(any(Department_entity.class))).thenReturn(updatedDept);

        Department_entity result = department_servicer.update_dept(updatedDept, 1);

        assertNotNull(result);
        assertEquals("p2m", result.getDept_name());
        assertEquals("product to market", result.getDept_function());
        assertEquals(102L, result.getDept_code());
        verify(dept_repo, times(1)).findById(1);
        verify(dept_repo, times(1)).save(any(Department_entity.class));
    }

    @Test
    public void testUpdateDepartment_NotFound() {
        Department_entity updatedDept = new Department_entity();
        updatedDept.setDept_name("p2m");

        when(dept_repo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            department_servicer.update_dept(updatedDept, 1);
        });

        assertEquals("department with id 1 not found", exception.getMessage());
    }


    @Test
    public void testPatchDepartment() {
        Department_entity updatedDept = new Department_entity();
        updatedDept.setDept_name("P2M");

        when(dept_repo.findById(1)).thenReturn(Optional.of(department));
        when(dept_repo.save(any(Department_entity.class))).thenReturn(department);

        Department_entity result = department_servicer.patch_dept(1, updatedDept);

        assertNotNull(result);
        assertEquals("P2M", result.getDept_name());
        verify(dept_repo, times(1)).findById(1);
        verify(dept_repo, times(1)).save(any(Department_entity.class));
    }

    @Test
    public void testPatchDepartment_NotFound() {
        Department_entity updatedDept = new Department_entity();
        updatedDept.setDept_name("P2M");

        when(dept_repo.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            department_servicer.patch_dept(1, updatedDept);
        });

        assertEquals("Department with ID 1 not found", exception.getMessage());
    }


    @Test
    public void testDeleteDepartment() {
        when(dept_repo.existsById(1)).thenReturn(true);
        when(dept_repo.findById(1)).thenReturn(Optional.of(department));

        String result = department_servicer.delete_dept(1);

        assertEquals("Department with id 1 and all its employees have been deleted", result);
        verify(dept_repo, times(1)).deleteById(1);
        verify(dept_repo, times(1)).delete(department);
    }

    @Test
    public void testDeleteDepartment_NotFound() {
        when(dept_repo.existsById(1)).thenReturn(false);

        String result = department_servicer.delete_dept(1);

        assertEquals("Department with id 1 not found", result);
        verify(dept_repo, times(1)).existsById(1);
        verify(dept_repo, times(0)).deleteById(1);
    }


}





