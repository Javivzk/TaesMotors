package com.svalero.taesmotors.repository;

import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    List<User> findByEmployee(Employee employee);

}
