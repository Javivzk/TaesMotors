package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.ClientNotFoundException;

import java.util.List;
import java.util.Map;

public interface CarService {

    List<Car> findAll();
    Car addCar(Car car);
    void deleteCar(long carId);
    Car findById(long carId) throws CarNotFoundException;
    Car patchCar(long carId, Map<String, Object> updates) throws CarNotFoundException;
    Car modifyCar(long carId, Car newCar) throws CarNotFoundException;
}
