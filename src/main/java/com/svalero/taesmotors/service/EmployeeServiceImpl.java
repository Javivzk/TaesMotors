package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee findById(long employeeId) throws EmployeeNotFoundException {
        logger.info("Employee id: " + employeeId);
        return employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee modifyEmployee(long employeeId, Employee newEmployee) throws EmployeeNotFoundException {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);
        logger.info("Employee to modify: " + existingEmployee);
        existingEmployee.setName(newEmployee.getName());
        existingEmployee.setLastName(newEmployee.getLastName());
        existingEmployee.setEmail(newEmployee.getEmail());
        existingEmployee.setPhone(newEmployee.getPhone());
        existingEmployee.setAddress(newEmployee.getAddress());
        existingEmployee.setPostalCode(newEmployee.getPostalCode());
        existingEmployee.setCity(newEmployee.getCity());
        existingEmployee.setEstado(newEmployee.getEstado());


        logger.info("Employee modified: " + newEmployee);
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    public Employee patchEmployee(long employeeId, Map<String, Object> updates) throws EmployeeNotFoundException {
        Employee existingEmployee = findById(employeeId);

        if (updates.containsKey("name")) {
            existingEmployee.setName((String) updates.get("name"));
        }
        if (updates.containsKey("lastName")) {
            existingEmployee.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("email")) {
            existingEmployee.setLastName((String) updates.get("email"));
        }
        if (updates.containsKey("phone")) {
            existingEmployee.setLastName((String) updates.get("phone"));
        }
        if (updates.containsKey("address")) {
            existingEmployee.setLastName((String) updates.get("address"));
        }
        if (updates.containsKey("postalCode")) {
            existingEmployee.setLastName((String) updates.get("postalCode"));
        }
        if (updates.containsKey("city")) {
            existingEmployee.setLastName((String) updates.get("city"));
        }
        if (updates.containsKey("estado")) {
            existingEmployee.setEstado((Boolean) updates.get("estado"));
        }
        return modifyEmployee(employeeId, existingEmployee);
    }
}
