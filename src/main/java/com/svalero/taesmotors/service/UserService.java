package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.domain.User;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.exception.ExtraNotFoundException;
import com.svalero.taesmotors.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    User addUser(User user);
    void deleteUser(long userId);
    User findById(long userId) throws UserNotFoundException;
    User patchUser(long userId, Map<String, Object> updates) throws UserNotFoundException, ExtraNotFoundException;
    User modifyUser(long userId, User newUser) throws UserNotFoundException, ExtraNotFoundException;
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    List<User> findByEmployee(Employee employee);

}
