package com.svalero.taesmotors.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private Long carId;
    private String brand;
    private String model;
    private String year;
    private String motor;
    private String fuel;
    private String color;
    private Double basePrice;
}

