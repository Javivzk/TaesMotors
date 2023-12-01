package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
    List<Employee> findByName(String name);
    List<Employee> findByLastName(String lastName);
}
