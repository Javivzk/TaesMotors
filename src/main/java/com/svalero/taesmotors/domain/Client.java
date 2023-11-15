package com.svalero.taesmotors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private boolean estado;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private String postalCode;
    @Column
    private String city;
}
