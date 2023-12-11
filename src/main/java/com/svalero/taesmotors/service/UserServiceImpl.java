package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.domain.Order;
import com.svalero.taesmotors.domain.User;
import com.svalero.taesmotors.exception.ExtraNotFoundException;
import com.svalero.taesmotors.exception.UserNotFoundException;
import com.svalero.taesmotors.repository.EmployeeRepository;
import com.svalero.taesmotors.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findById(long userId) throws UserNotFoundException {
        logger.info("User id: " + userId);
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findByEmployee(Employee employee) {
        return userRepository.findByEmployee(employee);
    }

    @Override
    public User modifyUser(long userId, User newUser) throws UserNotFoundException, ExtraNotFoundException {


        User existingUser = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        // Verificar si el usuario está activo antes de permitir modificaciones


        logger.info("User to modify: " + existingUser);
        existingUser.setUsername(newUser.getUsername());
        existingUser.setPassword(newUser.getPassword());
        existingUser.setName(newUser.getName());
        existingUser.setSurname(newUser.getSurname());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setActive(newUser.isActive());
        existingUser.setEmployee(newUser.getEmployee());


        logger.info("User modified: " + newUser);
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    public User patchUser(long userId, Map<String, Object> updates) throws UserNotFoundException, ExtraNotFoundException {
        User existingUser = findById(userId);

        if (updates.containsKey("username")) {
            existingUser.setUsername((String) updates.get("username"));
        }
        if (updates.containsKey("password")) {
            existingUser.setPassword((String) updates.get("password"));
        }
        if (updates.containsKey("name")) {
            existingUser.setName((String) updates.get("name"));
        }
        if (updates.containsKey("surname")) {
            existingUser.setSurname((String) updates.get("surname"));
        }
        if (updates.containsKey("email")) {
            existingUser.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("active")) {
            existingUser.setActive((Boolean) updates.get("active"));
        }
        if (updates.containsKey("employee")) {
            existingUser.setEmployee((Employee) updates.get("employee"));
        }


        return modifyUser(userId, existingUser);
    }
}
