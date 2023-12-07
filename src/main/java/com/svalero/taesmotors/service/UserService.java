package com.svalero.taesmotors.service;



import com.svalero.taesmotors.domain.User;
import com.svalero.taesmotors.domain.dto.UserDTO;

import java.util.Set;

/**
 * Service para gestión de usuarios
 */
public interface UserService {

    Set<User> findAll();
    User findByUsername(String username);
    Set<User> findByCity(String city);

    User addUser(UserDTO user);
    void remove(User user);

}