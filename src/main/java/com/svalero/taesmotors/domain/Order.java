package com.svalero.taesmotors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "forma_pago")
    private String formaPago;
    @Column(name = "paid")
    private boolean paid;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
