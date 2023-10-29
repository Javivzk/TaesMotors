package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.exception.CarNotFoundException;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car addCar(Car car);
    void deleteCar(String carId);
    Car findById(String carId) throws CarNotFoundException;

    Car modifyCar(String carId, Car newCar) throws CarNotFoundException;
}
