package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee addEmployee(Employee employee);
    void deleteEmployee(String employeeId);
    Employee findById(String employeeId) throws EmployeeNotFoundException;

    Employee modifyEmployee(String employeeId, Employee newEmployee) throws EmployeeNotFoundException;
}
