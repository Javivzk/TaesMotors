package com.svalero.taesmotors.domain.dto;

import com.svalero.taesmotors.domain.Employee;
import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Employee employee;
    private Boolean active;
}