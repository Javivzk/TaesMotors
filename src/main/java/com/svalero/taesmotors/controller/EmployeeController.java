package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.service.CarService;
import com.svalero.taesmotors.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException, NumberFormatException{
        logger.info("GET EMPLOYEE");
        Employee employee = employeeService.findById(employeeId);
        logger.info("END GET EMPLOYEE");
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable long employeeId, @Valid @RequestBody Employee employee) throws EmployeeNotFoundException {
        logger.error("PUT EMPLOYEE");
        Employee newEmployee = employeeService.modifyEmployee(employeeId,employee);
        logger.error("END PUT EMPLOYEE");
        return ResponseEntity.status(HttpStatus.OK).body(newEmployee);
    }

    @DeleteMapping(value = "/employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}