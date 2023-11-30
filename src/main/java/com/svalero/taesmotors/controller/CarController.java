package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.ErrorMessage;
import com.svalero.taesmotors.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CarController {

    private final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars(@RequestParam Map<String, String> data) {
        logger.info("GET Cars");

        if (data.isEmpty()) {
            logger.info("END GET Cars");
            return ResponseEntity.ok(carService.findAll());
        } else if (data.containsKey("brand")) {
            List<Car> cars = carService.findByBrand(data.get("brand"));
            logger.info("END GET Cars");
            return ResponseEntity.ok(cars);
        } else if (data.containsKey("model")) {
            List<Car> cars = carService.findByModel(data.get("model"));
            logger.info("END GET Cars");
            return ResponseEntity.ok(cars);
        } else {
            logger.error("BAD REQUEST");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/cars/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable long carId) throws CarNotFoundException, NumberFormatException{
        logger.info("GET CAR");
        Car car = carService.findById(carId);
        logger.info("END GET CAR");
        return ResponseEntity.ok(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car) {
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

    @PatchMapping("/cars/{carId}")
    public ResponseEntity<Car> patchCar(@PathVariable long carId, @RequestBody Map<String, Object> updates) throws CarNotFoundException {
        logger.error("PATCH CAR");

        Car updatedCar = carService.patchCar(carId, updates);

        logger.error("END PATCH CAR");
        return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
    }

    @DeleteMapping(value = "/cars/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable long carId) throws CarNotFoundException {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<?> handleCarNotFoundException(CarNotFoundException cnfe){
        logger.error("Car not found");
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error("Incorrect data");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error("Internal error " + e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
