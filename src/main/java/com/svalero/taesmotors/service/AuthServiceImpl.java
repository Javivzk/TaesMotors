package com.svalero.taesmotors.service;



import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.domain.User;
import com.svalero.taesmotors.domain.dto.UserDTO;
import com.svalero.taesmotors.exception.DuplicateEmailException;
import com.svalero.taesmotors.exception.DuplicateUsernameException;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.repository.EmployeeRepository;
import com.svalero.taesmotors.repository.RoleRepository;
import com.svalero.taesmotors.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

//    @Override
//    public boolean add(User user) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setCreationDate(LocalDate.now());
//        user.setActive(true);
//        Role userRole = roleRepository.findByName("user");
//        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
//        userRepository.save(user);
//
//        return true;
//    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public Set<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Set<User> findByCity(String city) {
        return null;
    }

    @Override
    public User addUser(UserDTO userDTO) throws DuplicateUsernameException, DuplicateEmailException, EmployeeNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        // Verifica si ya existe un usuario con el mismo username
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new DuplicateUsernameException("Username already exists");
        }

        // Verifica si ya existe un usuario con el mismo email
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        // Obtén el Employee si está presente, o establece null si no se proporciona en userDTO
        Employee employee = userDTO.getEmployee() != null ?
                employeeRepository.findById(userDTO.getEmployee().getEmployeeId())
                        .orElseThrow(EmployeeNotFoundException::new)
                : null;

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setCreationDate(LocalDate.now());
        user.setEmployee(employee);
        user.setActive(false);
        return userRepository.save(user);
    }

}