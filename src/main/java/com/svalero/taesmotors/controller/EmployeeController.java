package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.exception.ErrorMessage;
import com.svalero.taesmotors.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Map<String, String> data) {
        logger.info("GET Employees");

        if (data.isEmpty()) {
            logger.info("END GET Employees");
            return ResponseEntity.ok(employeeService.findAll());
        } else if (data.containsKey("name")) {
            List<Employee> employees = employeeService.findByName(data.get("name"));
            logger.info("END GET Employees");
            return ResponseEntity.ok(employees);
        } else if (data.containsKey("lastName")) {
            List<Employee> employees = employeeService.findByLastName(data.get("lastName"));
            logger.info("END GET Employees");
            return ResponseEntity.ok(employees);
        } else {
            logger.error("BAD REQUEST");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException, NumberFormatException{
        logger.info("GET EMPLOYEE");
        Employee employee = employeeService.findById(employeeId);
        logger.info("END GET EMPLOYEE");
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        logger.info("ADD Employee");
        Employee newEmployee = employeeService.addEmployee(employee);
        logger.info("END ADD Employee");
        return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable long employeeId, @Valid @RequestBody Employee employee) throws EmployeeNotFoundException {
        logger.error("PUT EMPLOYEE");
        Employee newEmployee = employeeService.modifyEmployee(employeeId,employee);
        logger.error("END PUT EMPLOYEE");
        return ResponseEntity.status(HttpStatus.OK).body(newEmployee);
    }

    @PatchMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable long employeeId, @RequestBody Map<String, Object> updates) throws EmployeeNotFoundException {
        logger.error("PATCH EMPLOYEE");
        Employee updatedEmployee = employeeService.patchEmployee(employeeId, updates);
        logger.error("END PATCH EMPLOYEE");
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @DeleteMapping(value = "/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleCarNotFoundException(EmployeeNotFoundException enfe){
        logger.error("Employee not found");
        ErrorMessage errorMessage = new ErrorMessage(404, enfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error("Incorrect data");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error("Internal error " + e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
