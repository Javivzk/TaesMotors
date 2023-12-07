package com.svalero.taesmotors.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String lastName;
    @Column
    @NotNull
    private Boolean clubMember;
    @Column
    @NotNull
    private String email;
    @Column
    @NotNull
    private String phone;
    @Column
    @NotNull
    private String address;
    @Column
    @NotNull
    private String postalCode;
    @Column
    @NotNull
    private String city;


}