package com.svalero.taesmotors.service;



import com.svalero.taesmotors.domain.User;
import com.svalero.taesmotors.domain.dto.UserDTO;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;

import java.util.Set;

/**
 * Service para gestión de usuarios
 */
public interface AuthService {

    Set<User> findAll();
    User findByUsername(String username);
    Set<User> findByCity(String city);

    User addUser(UserDTO user) throws EmployeeNotFoundException;
    void remove(User user);

}