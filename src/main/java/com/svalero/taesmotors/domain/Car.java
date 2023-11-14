package com.svalero.taesmotors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long carId;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String motor;
    @Column
    private String combustible;
    @Column
    private String color;
}
