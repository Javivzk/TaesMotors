package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CarController {

    private final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carService.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable long carId) throws CarNotFoundException, NumberFormatException{
        logger.info("GET CAR");
        Car car = carService.findById(carId);
        logger.info("END GET CAR");
        return ResponseEntity.ok(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carService.addCar(car);
        return new ResponseEntity<>(newCar,HttpStatus.CREATED);
    }

    @PutMapping("/cars/{carId}")
    public ResponseEntity<Car> modifyCar(@PathVariable long carId, @Valid @RequestBody Car car) throws CarNotFoundException {
        logger.error("PUT CAR");
        Car newCar = carService.modifyCar(carId,car);
        logger.error("END PUT CAR");
        return ResponseEntity.status(HttpStatus.OK).body(newCar);
    }

    @DeleteMapping(value = "/cars/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
}
