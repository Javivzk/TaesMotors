package com.svalero.taesmotors.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String year;
    @Column
    private String motor;
    @Column
    private String fuel;
    @Column
    private String color;
    @Column
    private Double basePrice;

}