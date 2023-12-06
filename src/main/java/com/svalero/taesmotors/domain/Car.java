package com.svalero.taesmotors.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String brand;
    @Column
    @NotNull
    private String image;
    @Column
    @NotNull
    private String model;
    @Column
    @NotNull
    private String year;
    @Column
    @NotNull
    private String motor;
    @Column
    @NotNull
    private String fuel;
    @Column
    @NotNull
    private String color;
    @Column
    @NotNull
    private Double basePrice;
    @Column
    private Boolean stock;

}