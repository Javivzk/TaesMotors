package com.svalero.taesmotors.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String nif;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String postalCode;
    @Column
    private String province;
    @Column
    private String country;
    @Column
    private String image;
    @Column
    private LocalDate creationDate;
    @Column
    private LocalDateTime lastLogin;
    @Column
    private boolean active = true;
    @Transient
    private int age;


    @OneToOne
    private Employee employee;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
