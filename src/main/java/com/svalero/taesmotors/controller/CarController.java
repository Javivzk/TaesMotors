package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.repository.CarRepository;
import com.svalero.taesmotors.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }
}
