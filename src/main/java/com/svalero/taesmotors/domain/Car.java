package com.svalero.taesmotors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("car")
public class Car {

    @Id
    private String id;

    private String brand;

    private String model;

    private String motor;

    private String combustible;

    private String color;
}
