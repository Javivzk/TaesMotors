package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;

import java.util.List;

public interface CarService {

    List<Car> findAllByBrand();
}
