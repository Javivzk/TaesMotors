package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.exception.CarNotFoundException;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car addCar(Car car);
    void deleteCar(long carId);
    Car findById(long carId) throws CarNotFoundException;

    Car modifyCar(long carId, Car newCar) throws CarNotFoundException;
}
