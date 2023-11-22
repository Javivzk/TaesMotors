package com.svalero.taesmotors.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long carId;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "motor")
    private String motor;
    @Column(name = "stock")
    private boolean stock;
    @Column(name = "combustible")
    private String combustible;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private double price;
    @Column(name = "year")
    private String year;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "car")
    private Order order;

}
