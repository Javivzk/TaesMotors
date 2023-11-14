package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee addEmployee(Employee employee);
    void deleteEmployee(long employeeId);
    Employee findById(long employeeId) throws EmployeeNotFoundException;

    Employee modifyEmployee(long employeeId, Employee newEmployee) throws EmployeeNotFoundException;
}
